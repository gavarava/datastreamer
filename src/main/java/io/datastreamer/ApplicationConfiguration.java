package io.datastreamer;

import io.datastreamer.adapters.database.OrderEntity;
import io.datastreamer.adapters.database.PostgresBigOrderRepository;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Profile({"prod", "test"})
@Configuration
@EnableJpaRepositories(basePackages = {"io.datastreamer.adapters"})
class ApplicationConfiguration {

  @Bean
  PostgresBigOrderRepository postgresBigOrderRepository(@Autowired EntityManager entityManager) {
    return new PostgresBigOrderRepository(OrderEntity.class, entityManager);
  }
}
