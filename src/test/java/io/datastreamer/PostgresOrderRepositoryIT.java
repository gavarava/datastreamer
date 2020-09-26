package io.datastreamer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.datastreamer.adapters.database.PostgresOrderRepository;
import io.datastreamer.core.model.Order;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
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

    List<Order> slicedList = orders
        .stream()
        .filter(order -> order.getOrderTotal()
            .compareTo(BigDecimal.valueOf(400)) == -1)
        .collect(Collectors.toList());

    System.out.println(
        slicedList.stream().mapToDouble(value -> value.getOrderTotal().doubleValue()).sum());

    assertThat(slicedList.size(), is(399260));

    long timetaken = System.currentTimeMillis() - startTime;
    log.info("Time taken in milliseconds = {}", timetaken);
  }

}