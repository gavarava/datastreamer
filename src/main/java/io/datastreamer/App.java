package io.datastreamer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    log.info("Running Data Streamer");
    SpringApplication.run(App.class, args);
  }
}
