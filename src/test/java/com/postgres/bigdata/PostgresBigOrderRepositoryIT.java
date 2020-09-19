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

  @Test
  void findAll() {
  }

  @Transactional
  @Test
  void testFindAll() {
    Specification<OrderEntity> orderSpecification = OrderSpecification
        .orderTotalLessThan(BigDecimal.valueOf(400));

    List<OrderEntity> result = postgresBigOrderRepository
        .stream(orderSpecification, OrderEntity.class)
        .filter(orderEntity -> orderEntity.getOrdertotal().compareTo(BigDecimal.valueOf(300)) == -1)
        .collect(Collectors.toList());
    System.out.println(result);
  }

  @Test
  void shouldReturnPagesOfLargeDataSet() {

    Pageable paging = PageRequest.of(0, 400);
    Page<OrderEntity> orderEntities = postgresBigOrderRepository.findAll(paging);
    List<String> customerNames = orderEntities.stream()
        .map(orderEntity -> orderEntity.getCustomername())
        .collect(Collectors.toList());

    assertThat(customerNames.size(), is(400));
    // System.out.println(customerNames);
  }

  @Test
  void shouldReturnMultiplePagesOfData_whenAskingForOrderTotalsLessThan_400() {
    Specification<OrderEntity> orderSpecification = OrderSpecification
        .orderTotalLessThan(BigDecimal.valueOf(400));

    int maxPages = Integer.MAX_VALUE;

    for (int i = 0; i < maxPages; i++) {

    }

    Pageable paging = PageRequest.of(0, 1000);
    Page<OrderEntity> orderEntities = postgresBigOrderRepository
        .findAll(orderSpecification, paging);
    List<BigDecimal> orderTotals = orderEntities.stream()
        .map(orderEntity -> orderEntity.getOrdertotal())
        .collect(Collectors.toList());

    System.out.println(orderTotals.get(0));
    System.out.println(orderTotals.get(orderTotals.size() - 1));
    assertThat(orderTotals.size(), is(1000));
  }


  @Test
  void shouldReturnPageOfData_whenAskingForOrderTotalsLessThan_400() {
    Specification<OrderEntity> orderSpecification = OrderSpecification
        .orderTotalLessThan(BigDecimal.valueOf(400));
    Pageable paging = PageRequest.of(0, 1000);
    Page<OrderEntity> orderEntities = postgresBigOrderRepository
        .findAll(orderSpecification, paging);
    List<BigDecimal> orderTotals = orderEntities.stream()
        .map(orderEntity -> orderEntity.getOrdertotal())
        .collect(Collectors.toList());

    System.out.println(orderTotals.get(0));
    System.out.println(orderTotals.get(orderTotals.size() - 1));
    assertThat(orderTotals.size(), is(1000));
  }

}