package com.postgres.bigdata;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
  void findById() {
  }
}