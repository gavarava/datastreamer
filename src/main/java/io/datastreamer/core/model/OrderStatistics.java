package io.datastreamer.core.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderStatistics {

  OrderCountsUnderThreshold orderCountsUnderThreshold;
  OrderCountsAboveThreshold orderCountsAboveThreshold;
}
