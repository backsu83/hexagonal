package com.ebaykorea.payback.core.domain.entity.reward;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class RewardT2T3SmileCardCashbackPolicy {
  //buyOrderNo
  long policyKey;
  /** 스마일카드 T2,T3 일때 금액 **/
  BigDecimal cashbackAmount;
}
