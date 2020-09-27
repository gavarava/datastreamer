package io.datastreamer.core.services;

import io.datastreamer.adapters.database.OrderSpecification;
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

  public long getOrdersUnder(BigDecimal highestOrderTotal) {
    return repository.count(OrderSpecification.orderTotalLessThanOrEqualTo(highestOrderTotal));
  }

}
