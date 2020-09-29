package io.datastreamer.adapters.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.datastreamer.core.model.OrderCountsAboveThreshold;
import io.datastreamer.core.services.OrderCountsStreamingService;
import java.math.BigDecimal;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@Profile(value = "default")
@Slf4j
public class OrderAnalyticsProducer extends KafkaProducer<String, String> {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private OrderCountsStreamingService orderCountsStreamingService;

  @Autowired
  private ObjectMapper objectMapper;

  public OrderAnalyticsProducer(Map configs) {
    super(configs);
  }

  @PostConstruct
  public void sendOrderMessage() throws JsonProcessingException {
    log.info("Running sendOrderMessage");

    int threshold = 5;
    long orderCounts = Long.MAX_VALUE;

    while (orderCounts != 0) {

      OrderCountsAboveThreshold orderCountsAboveThreshold = orderCountsStreamingService
          .getOrdersOver(BigDecimal.valueOf(threshold));

      threshold += 5;

      String topicName = "order-totals";
      String orderMessage = objectMapper.writeValueAsString(orderCountsAboveThreshold);
      log.info("Sending ---> orderMessage = {}", orderMessage);
      kafkaTemplate.send(topicName, orderMessage);
    }
  }
}
