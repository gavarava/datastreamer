package com.postgres.bigdata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class PostgresBigOrderRepositoryIT {

  @Autowired
  private PostgresBigOrderRepository postgresBigOrderRepository;

  @Transactional
  @Test
  void testFindAll_whenNotUsingPagination_andLoadingAllResultSetInMemory() {
    Specification<OrderEntity> orderSpecification = OrderSpecification
        .orderTotalLessThan(BigDecimal.valueOf(400));

    List<OrderEntity> result = postgresBigOrderRepository
        .stream(orderSpecification, OrderEntity.class)
        .collect(Collectors.toList());
    System.out.println("Total Elements = " + result.size());
    assertThat(result.size(), is(399260));

    double batchOrderTotal = result.stream()
        .mapToDouble(value -> value.getOrdertotal().doubleValue()).sum();
    System.out.println("Order totals of this batch = " + batchOrderTotal);
  }

  @Test
  void shouldReturnMultiplePagesOfData_whenAskingForOrderTotalsLessThan_400() {
    Specification<OrderEntity> orderSpecification = OrderSpecification
        .orderTotalLessThan(BigDecimal.valueOf(400));

    Pageable paging = PageRequest.of(0, 20000);
    Page<OrderEntity> orderEntities = postgresBigOrderRepository
        .findAll(orderSpecification, paging);

    System.out.println("Total Elements = " + orderEntities.getTotalElements());
    assertThat(orderEntities.getTotalElements(), is(399260L));

    System.out.println("Total Pages = " + orderEntities.getTotalPages());
    System.out.println("Number = " + orderEntities.getNumber());

    double batchOrderTotal = orderEntities.stream()
        .mapToDouble(value -> value.getOrdertotal().doubleValue()).sum();
    System.out.println("Order totals of this batch = " + batchOrderTotal);



    for (int i = 1; i < orderEntities.getTotalPages(); i++) {
      orderEntities = postgresBigOrderRepository
          .findAll(orderSpecification, orderEntities.nextPageable());
      System.out.println("Page Number = " + orderEntities.getNumber());
      System.out.println("Elements in this page = " + orderEntities.getNumberOfElements());

      batchOrderTotal += orderEntities.stream()
          .mapToDouble(value -> value.getOrdertotal().doubleValue()).sum();
      System.out.println("Order totals of this batch = " + batchOrderTotal);
    }

    System.out.println("Final batchOrderTotal = " +  batchOrderTotal);
  }
}
