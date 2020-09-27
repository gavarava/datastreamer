package io.datastreamer.adapters.kafka;

import io.datastreamer.core.services.OrderCountsStreamingService;
import java.math.BigDecimal;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class OrderTotalsProducer extends KafkaProducer<String, String> {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private OrderCountsStreamingService orderCountsStreamingService;

  public OrderTotalsProducer(Map configs) {
    super(configs);
  }

  @PostConstruct
  public void sendOrderMessage() {
    log.info("Running sendOrderMessage");
    int orderTotalThreshold = 5;
    long orderCounts = Long.MAX_VALUE;
    while (orderCounts != 0 && orderCounts != 1000000) {
      orderCounts = orderCountsStreamingService
          .getOrdersUnder(BigDecimal.valueOf(orderTotalThreshold));
      orderTotalThreshold += 5;
      log.info("orderCounts = {} for orderTotalThreshold {}", orderCounts, orderTotalThreshold);
      String topicName = "order-totals";
      String orderMessage = "{orderTotalThreshold : "+ orderTotalThreshold+ ", orderCounts: "+ orderCounts+ "}";
      kafkaTemplate.send(topicName, orderMessage);
    }
  }
}
