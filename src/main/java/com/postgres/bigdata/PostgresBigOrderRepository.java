package com.postgres.bigdata;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Streamable repository methods https://itnext.io/scrolling-through-large-datasets-in-spring-data-jpa-with-streams-and-specification-2fd975129758
 */
public class PostgresBigOrderRepository implements BigOrderRepository {

  private JdbcTemplate jdbcTemplateObject;

  @Autowired
  private EntityManager entityManager;

  public PostgresBigOrderRepository(DataSource dataSource) {
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public Iterable<OrderEntity> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<OrderEntity> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends OrderEntity> S save(S s) {
    return null;
  }

  @Override
  public <S extends OrderEntity> Iterable<S> saveAll(Iterable<S> iterable) {
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
  public Iterable<OrderEntity> findAll() {
    return null;
  }

  @Override
  public Iterable<OrderEntity> findAllById(Iterable<String> iterable) {
    return null;
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
  public void deleteAll(Iterable<? extends OrderEntity> iterable) {

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

  @Override
  public Optional<OrderEntity> findOne(Specification<OrderEntity> specification) {
    return Optional.empty();
  }

  @Override
  public List<OrderEntity> findAll(Specification<OrderEntity> specification) {
    return null;
  }

  @Override
  public Page<OrderEntity> findAll(Specification<OrderEntity> specification, Pageable pageable) {
    return null;
  }

  @Override
  public List<OrderEntity> findAll(Specification<OrderEntity> specification, Sort sort) {
    return null;
  }

  @Override
  public long count(Specification<OrderEntity> specification) {
    return 0;
  }
}
