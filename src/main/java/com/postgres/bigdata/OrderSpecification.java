package com.postgres.bigdata;

import java.math.BigDecimal;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public abstract class OrderSpecification {

  public static Specification<OrderEntity> orderTotalLessThan(BigDecimal amount) {
    return (Specification<OrderEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
        .lessThanOrEqualTo(root.get("ordertotal"), amount);
  }
}


