package com.ebaykorea.payback.infrastructure.gateway.client.transaction.dto;

import lombok.Data;

@Data
public class KeyMapDto {
  private String txKey;
  private String orderKey;
  private Long packNo;
  private String orderUnitKey;
  private Long orderNo; //buyOrderNo
  private Long contrNo;
}
