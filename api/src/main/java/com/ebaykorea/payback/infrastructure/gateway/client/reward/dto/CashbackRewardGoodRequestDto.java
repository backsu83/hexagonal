package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CashbackRewardGoodRequestDto {
  private String key;
  private Integer siteCd;
  private String gdNo;
  private String gdlcCd;
  private String gdmcCd;
  private String gdscCd;
  private String scNo;
  private Boolean isSmileClub;
  private Boolean isSmileDelivery;
  private Boolean isSmileFresh;
  private Integer qty;
  private Integer price;
  private String marketabilityItemYn;
}
