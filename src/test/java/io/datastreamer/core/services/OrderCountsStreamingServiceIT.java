package io.datastreamer.core.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class OrderCountsStreamingServiceIT {

  @Autowired
  private OrderCountsStreamingService orderCountsStreamingService;

  @Test
  void testOrderStreamingService() {
    long result = orderCountsStreamingService.getOrdersUnder(BigDecimal.valueOf(500));
    assertThat(result, is(499843L));
  }


}