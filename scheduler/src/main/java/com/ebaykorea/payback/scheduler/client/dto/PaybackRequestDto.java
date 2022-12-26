package com.ebaykorea.payback.scheduler.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaybackRequestDto {
  private String orderKey;
  private String txKey;
}
