package io.datastreamer.core.services;

import io.datastreamer.adapters.database.OrderSpecification;
import io.datastreamer.adapters.database.PostgresBigOrderRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier(value = "postgresBigOrderRepository")
@Service
public class OrderCountsStreamingService {

  private final PostgresBigOrderRepository repository;

  @Autowired
  public OrderCountsStreamingService(PostgresBigOrderRepository repository) {
    this.repository = repository;
  }

  public long getOrdersUnder(BigDecimal highestOrderTotal) {
    return repository.count(OrderSpecification.orderTotalLessThanOrEqualTo(highestOrderTotal));
  }

}
