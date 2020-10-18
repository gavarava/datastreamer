package io.datastreamer.adapters.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.datastreamer.core.model.OrderStatistics;
import io.datastreamer.core.services.OrderCountsStreamingService;
import java.math.BigDecimal;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@Profile("prod")
@Slf4j
public class OrderStatisticsProducer extends KafkaProducer<String, String> {

  private static final String ORDER_STATS = "order-stats";
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private OrderCountsStreamingService orderCountsStreamingService;

  @Autowired
  private ObjectMapper objectMapper;

  public OrderStatisticsProducer(Map configs) {
    super(configs);
  }

  @PostConstruct
  public void publishOrderStatisticsMessage() throws JsonProcessingException {
    log.debug("Running publishOrderStatisticsMessage");

    int threshold = 5;
    long entriesCounter = Long.MAX_VALUE;

    while (entriesCounter != 0) {
      threshold += 5;

      OrderStatistics orderStatistics = OrderStatistics.builder()
          .orderCountsAboveThreshold(orderCountsStreamingService
              .getOrdersOver(BigDecimal.valueOf(threshold)))
          .orderCountsUnderThreshold(orderCountsStreamingService
              .getOrdersUnder(BigDecimal.valueOf(threshold)))
          .build();

      String orderStatsMessage = objectMapper.writeValueAsString(orderStatistics);
      log.info("publishing => orderStatsMessage = {}", orderStatsMessage);
      kafkaTemplate.send(ORDER_STATS, orderStatsMessage);

      entriesCounter--;
    }
  }
}