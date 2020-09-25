package io.datastreamer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Order {

  String id;
  OffsetDateTime creationDate;
  String customerName;
  BigDecimal orderTotal;
  String membershipLevel;

}
