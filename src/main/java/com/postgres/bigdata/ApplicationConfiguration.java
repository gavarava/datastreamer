package com.postgres.bigdata;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@EnableJpaRepositories
class ApplicationConfiguration {

  @Bean
  public DataSource dataSource() {
    SimpleDriverDataSource driverDataSource = new SimpleDriverDataSource();
    driverDataSource.setDriver(new Driver());
    driverDataSource
        .setUrl("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=password");
    driverDataSource.setUsername("postgres");
    driverDataSource.setPassword("password");
    return driverDataSource;
  }

  @Bean
  NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }

  @Bean
  PostgresOrderRepository postgreOrderRepository() {
    return new PostgresOrderRepository(dataSource());
  }

  @Bean
  PostgresBigOrderRepository postgresBigOrderRepository(@Autowired EntityManager entityManager) {
    return new PostgresBigOrderRepository(OrderEntity.class, entityManager);
  }
}
