package io.datastreamer;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BigOrderRepository extends PagingAndSortingRepository<OrderEntity, String>,
    JpaSpecificationExecutor<OrderEntity>, StreamableJpaSpecificationRepository<OrderEntity> {

}
