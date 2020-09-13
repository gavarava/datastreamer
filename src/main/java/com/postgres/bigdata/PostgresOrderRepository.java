package com.postgres.bigdata;

import static java.time.Instant.ofEpochMilli;
import static java.time.OffsetDateTime.ofInstant;
import static java.time.ZoneOffset.UTC;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class PostgresOrderRepository implements OrderRepository {

  private JdbcTemplate jdbcTemplateObject;

  public PostgresOrderRepository(DataSource dataSource) {
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public void create(String orderId, OffsetDateTime creationDate, String customerName,
      BigDecimal orderTotal, String membershipLevel) {
    String SQL = "INSERT INTO public.order (creationDate, customerName, orderTotal, membershipLevel) values (?, ?, ?, ?)";
    jdbcTemplateObject
        .update(SQL, orderId, creationDate, customerName, orderTotal, membershipLevel);
  }

  @Override
  public Order getOrder(String orderId) {
    String SQL = "select * from public.order where id = ?";
    jdbcTemplateObject.query(SQL, resultSet -> {
      return new Order.OrderBuilder()
          .orderId(resultSet.getString("id"))
          .creationDate(ofInstant(ofEpochMilli(resultSet.getTimestamp("creationDate").getTime()),
              UTC))
          .customerName(resultSet.getString("customerName"))
          .orderTotal(resultSet.getBigDecimal("orderTotal"))
          .membershipLevel(resultSet.getString("membershipLevel"))
          .build();
    });
    return null;
  }

  @Override
  public List<Order> listOrders() {
    String SQL = "select * from public.order";
    List<Order> orders = new ArrayList<>();
    jdbcTemplateObject.query(SQL, resultSet -> {
      Order order = new Order.OrderBuilder()
          .orderId(resultSet.getString("id"))
          .creationDate(ofInstant(ofEpochMilli(resultSet.getTimestamp("creationDate").getTime()),
              UTC))
          .customerName(resultSet.getString("customerName"))
          .orderTotal(resultSet.getBigDecimal("orderTotal"))
          .membershipLevel(resultSet.getString("membershipLevel"))
          .build();
      orders.add(order);
    });
    return orders;
  }
}
