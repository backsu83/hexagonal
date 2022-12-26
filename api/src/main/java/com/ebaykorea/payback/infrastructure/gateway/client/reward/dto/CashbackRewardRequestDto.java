package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;;

import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CashbackRewardRequestDto {
  Integer totalPrice;
  List<CashbackRewardGoodRequestDto> goods;
}