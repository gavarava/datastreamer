package com.postgres.bigdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

  public static void main(String[] args) throws SQLException {
    Connection connection = connectToDatabase();
    if (!connection.isClosed()) {
      System.out.println("Connected to Database!!");
    } else {
      System.out.println("Could not reach DB");
    }

    // Create a Stream of Domain Objects from data to play with




    connection.close();
  }

  private static Connection connectToDatabase() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=password";
    Connection conn = DriverManager.getConnection(url);
    return conn;
  }
}
