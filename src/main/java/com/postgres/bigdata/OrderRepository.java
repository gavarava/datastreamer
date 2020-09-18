package com.postgres.bigdata;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public interface OrderRepository {

  void create(String orderId, OffsetDateTime creationDate, String customerName,
      BigDecimal orderTotal, String membershipLevel);

  Order getOrder(String orderId);

  List<Order> listOrders();

}
