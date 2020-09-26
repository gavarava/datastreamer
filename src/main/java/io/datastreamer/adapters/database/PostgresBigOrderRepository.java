package io.datastreamer.adapters.database;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

import io.datastreamer.ports.BigOrderRepository;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Streamable repository methods https://itnext.io/scrolling-through-large-datasets-in-spring-data-jpa-with-streams-and-specification-2fd975129758
 */
public class PostgresBigOrderRepository extends SimpleJpaRepository<OrderEntity, String> implements
    BigOrderRepository {

  @Autowired
  private EntityManager entityManager;

  public PostgresBigOrderRepository(
      JpaEntityInformation entityInformation,
      EntityManager entityManager) {
    super(entityInformation, entityManager);
  }

  public PostgresBigOrderRepository(Class domainClass, EntityManager em) {
    super(domainClass, em);
  }


  @Override
  public <S extends OrderEntity> S save(S s) {
    return null;
  }

  @Override
  public Optional<OrderEntity> findById(String s) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(String s) {
  }

  @Override
  public void delete(OrderEntity OrderEntity) {
  }


  @Override
  public void deleteAll() {
  }

  @Override
  public Stream<OrderEntity> stream(Specification<OrderEntity> specification,
      Class<OrderEntity> clazz) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<OrderEntity> criteriaQuery = criteriaBuilder.createQuery(clazz);
    Root<OrderEntity> root = criteriaQuery.from(clazz);
    criteriaQuery.select(root);
    // do some sanity checks for your pleasure
    if (specification != null) {
      criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
    }
    TypedQuery<OrderEntity> orderEntityTypedQuery = entityManager.createQuery(criteriaQuery)
        .setHint(HINT_FETCH_SIZE,
            "50").setFirstResult(0);
    return orderEntityTypedQuery.getResultStream();
  }
}
