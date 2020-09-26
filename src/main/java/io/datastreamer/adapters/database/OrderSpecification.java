package io.datastreamer.adapters.database;

import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

public abstract class OrderSpecification {

  private static final String ORDERTOTAL_COLUMN = "ordertotal";

  public static Specification<OrderEntity> orderTotalLessThan(BigDecimal amount) {
    return (Specification<OrderEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
        .lessThan(root.get(ORDERTOTAL_COLUMN), amount);
  }

  public static Specification<OrderEntity> orderTotalLessThanOrEqualTo(BigDecimal amount) {
    return (Specification<OrderEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
        .lessThanOrEqualTo(root.get(ORDERTOTAL_COLUMN), amount);
  }

  public static Specification<OrderEntity> orderTotalGreaterThan(BigDecimal amount) {
    return (Specification<OrderEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
        .greaterThan(root.get(ORDERTOTAL_COLUMN), amount);
  }

  public static Specification<OrderEntity> orderTotalGreaterThanOrEqualTo(BigDecimal amount) {
    return (Specification<OrderEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
        .greaterThanOrEqualTo(root.get(ORDERTOTAL_COLUMN), amount);
  }
}
