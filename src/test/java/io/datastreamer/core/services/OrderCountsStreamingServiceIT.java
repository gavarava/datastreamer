package io.datastreamer.core.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import io.datastreamer.core.model.OrderCountsAboveThreshold;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles(profiles = "test")
@SpringBootTest
class OrderCountsStreamingServiceIT {

  @Autowired
  private OrderCountsStreamingService orderCountsStreamingService;

  @Test
  void testOrderStreamingService() {
    OrderCountsAboveThreshold countsUnderValue = orderCountsStreamingService
        .getOrdersOver(BigDecimal.valueOf(1001));
    Long result = countsUnderValue.getOrderCountAboveThreshold().longValue();
    assertThat(result, is(0L));
  }
}
