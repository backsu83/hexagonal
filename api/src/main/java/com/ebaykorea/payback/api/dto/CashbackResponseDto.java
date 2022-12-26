package com.ebaykorea.payback.api.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CashbackResponseDto {
  private String txKey;
  private String orderKey;

  public static CashbackResponseDto of(final String txKey, final String orderKey) {
    return new CashbackResponseDto(txKey, orderKey);
  }
}
