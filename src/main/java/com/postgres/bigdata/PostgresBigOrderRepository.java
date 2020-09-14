package com.postgres.bigdata;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Streamable repository methods
 * https://itnext.io/scrolling-through-large-datasets-in-spring-data-jpa-with-streams-and-specification-2fd975129758
 */
public class PostgresBigOrderRepository implements BigOrderRepository {

  private JdbcTemplate jdbcTemplateObject;

  public PostgresBigOrderRepository(DataSource dataSource) {
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public Iterable<Order> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<Order> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Order> S save(S s) {
    return null;
  }

  @Override
  public <S extends Order> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<Order> findById(String s) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public Iterable<Order> findAll() {
    return null;
  }

  @Override
  public Iterable<Order> findAllById(Iterable<String> iterable) {
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
  public void delete(Order order) {

  }

  @Override
  public void deleteAll(Iterable<? extends Order> iterable) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public Stream<Order> stream(Specification<Order> specification, Class<Order> clazz) {
    // Provide a Stream of data base on specification
    return null;
  }

  @Override
  public Optional<Order> findOne(Specification<Order> specification) {
    return Optional.empty();
  }

  @Override
  public List<Order> findAll(Specification<Order> specification) {
    return null;
  }

  @Override
  public Page<Order> findAll(Specification<Order> specification, Pageable pageable) {
    return null;
  }

  @Override
  public List<Order> findAll(Specification<Order> specification, Sort sort) {
    return null;
  }

  @Override
  public long count(Specification<Order> specification) {
    return 0;
  }
}
