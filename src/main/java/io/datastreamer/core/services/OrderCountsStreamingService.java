package io.datastreamer.core.services;

import io.datastreamer.adapters.database.OrderSpecification;
import io.datastreamer.core.model.OrderCountsAboveThreshold;
import io.datastreamer.core.model.OrderCountsUnderThreshold;
import io.datastreamer.ports.BigOrderRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCountsStreamingService {

  private final BigOrderRepository repository;

  @Autowired
  public OrderCountsStreamingService(BigOrderRepository repository) {
    this.repository = repository;
  }

  public OrderCountsUnderThreshold getOrdersUnder(BigDecimal highestOrderTotal) {
    long orderCount = repository
        .count(OrderSpecification.orderTotalLessThanOrEqualTo(highestOrderTotal));

    return OrderCountsUnderThreshold.builder()
        .threshold(highestOrderTotal)
        .orderCountUnderThreshold(BigDecimal.valueOf(orderCount)).build();
  }

  public OrderCountsAboveThreshold getOrdersOver(BigDecimal lowestOrderTotalToStartFrom) {
    long orderCount = repository
        .count(OrderSpecification.orderTotalGreaterThanOrEqualTo(lowestOrderTotalToStartFrom));

    return OrderCountsAboveThreshold.builder()
        .threshold(lowestOrderTotalToStartFrom)
        .orderCountAboveThreshold(BigDecimal.valueOf(orderCount)).build();
  }

}
