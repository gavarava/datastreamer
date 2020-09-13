package com.postgres.bigdata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class PostgresOrderRepositoryIT {

  @Autowired
  private PostgresOrderRepository postgresOrderRepository;

  @Test
  void shouldGiveLargeSetOfOrderData() {
    long startTime = System.currentTimeMillis();
    List<Order> orders = postgresOrderRepository.listOrders();
    assertThat(orders.size(), is(1000000));
    long timetaken = System.currentTimeMillis() - startTime;
    log.info("Time taken in milliseconds = {}", timetaken);
  }

}