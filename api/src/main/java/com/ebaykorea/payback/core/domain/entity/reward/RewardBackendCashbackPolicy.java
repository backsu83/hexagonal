package com.ebaykorea.payback.core.domain.entity.reward;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RewardBackendCashbackPolicy {
  //buyOrderNo
  long policyKey;

  //캐시백 정책번호
  Integer cashbackSeq;

  //캐시백 코드 ( 1 = ItemCashback, 2 = NewSmilePayCashback, 3 = SellerCashback, 4 = CartCashback )
  CashbackType cashbackCode;

  //자동충전결제 지급률
  BigDecimal chargePayRewardRate;

  //자동충전결제 클럽회원 지급률
  BigDecimal chargePayRewardClubRate;

  //자동충전결제 지급최대금액
  Integer chargePayRewardMaxMoney;

  //자동충전결제 클럽회원 지급최대금액
  Integer chargePayRewardClubMaxMoney;

  //클럽데이 지급률
  BigDecimal clubDayPayRate;

  //클럽데이 지급최대금액
  Integer clubDaySaveMaxMoney;
}
