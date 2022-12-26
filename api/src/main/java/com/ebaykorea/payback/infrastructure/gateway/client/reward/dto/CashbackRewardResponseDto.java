package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;


@Data
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CashbackRewardResponseDto {
  private Integer totalItemCashbackAmount;
  private Integer totalNSPCashbackAmount;
  private Integer ifSmileCardCashbackAmount;
  private Integer ifNewSmileCardCashbackAmount;
  private String useEnableDate;
  private List<CashbackRewardGoodResponseDto> goods;
}
