package com.postgres.bigdata;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BigOrderRepository extends PagingAndSortingRepository<Order, String>,
    JpaSpecificationExecutor<Order>, StreamableJpaSpecificationRepository<Order> {

}
