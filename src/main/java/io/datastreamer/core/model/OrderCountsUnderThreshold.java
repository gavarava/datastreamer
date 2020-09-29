package io.datastreamer.core.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderCountsUnderThreshold implements AnalyticsCriteria {

  private BigDecimal threshold;
  private BigDecimal orderCountUnderThreshold;
}
