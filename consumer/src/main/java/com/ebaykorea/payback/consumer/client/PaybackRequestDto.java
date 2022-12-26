package com.ebaykorea.payback.consumer.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaybackRequestDto {
  private String orderKey;
  private String txKey;
}
