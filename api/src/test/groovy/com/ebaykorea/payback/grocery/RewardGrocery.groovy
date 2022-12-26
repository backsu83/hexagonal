package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.core.domain.constant.CashbackType
import com.ebaykorea.payback.core.domain.entity.reward.RewardBackendCashbackPolicy
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicies
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicy
import com.ebaykorea.payback.core.domain.entity.reward.RewardT2T3SmileCardCashbackPolicy

class RewardGrocery {
  static def RewardCashbackPolicies_생성(Map map = [:]) {
    RewardCashbackPolicies.of(
        (map.cashbackPolicies ?: []) as List<RewardCashbackPolicy>,
        (map.backendCashbackPolicies ?: []) as List<RewardBackendCashbackPolicy>,
        (map.smileCardCashbackPolicies ?: []) as List<RewardT2T3SmileCardCashbackPolicy>,
        (map.useEnableDate ?: "2023-10-17") as String,
        (map.smileCardCashbackAmount ?: 0L) as BigDecimal,
        (map.newSmileCardCashbackAmount ?: 0L) as BigDecimal
    )
  }

  static def RewardCashbackPolicy_생성(Map map = [:]) {
    RewardCashbackPolicy.builder()
        .policyKey((map.policyKey ?: 1L) as long)
        .cashbackCd((map.cashbackCd ?: CashbackType.Item) as CashbackType)
        .cashbackAmount((map.cashbackAmount ?: 1000) as Integer)
        .cashbackSeq((map.cashbackSeq ?: 1L) as Long)
        .payType((map.payType ?: "P") as String)
        .payRate((map.payRate ?: 0L) as BigDecimal)
        .payMaxMoney((map.payMaxMoney ?: 0L) as BigDecimal)
        .cashbackTitle((map.cashbackTitle ?: "cashbackTitle") as String)
        .clubCashbackAmount((map.clubCashbackAmount ?: 0L) as BigDecimal)
        .autoChargeAmount((map.autoChargeAmount ?: 0L) as BigDecimal)
        .autoChargeClubAmount((map.autoChargeClubAmount ?: 0L) as BigDecimal)
        .build()
  }

  static def RewardBackendCashbackPolicy_생성(Map map = [:]) {
    RewardBackendCashbackPolicy.builder()
        .policyKey((map.policyKey ?: 1L) as long)
        .cashbackSeq((map.cashbackSeq ?: 1) as Integer)
        .cashbackCode((map.cashbackCode ?: CashbackType.Item) as CashbackType)
        .chargePayRewardRate((map.chargePayRewardRate ?: 0L) as BigDecimal)
        .chargePayRewardClubRate((map.chargePayRewardClubRate ?: 0L) as BigDecimal)
        .chargePayRewardMaxMoney((map.chargePayRewardMaxMoney ?: 0) as Integer)
        .chargePayRewardClubMaxMoney((map.chargePayRewardClubMaxMoney ?: 0) as Integer)
        .clubDayPayRate((map.clubDayPayRate ?: 0L) as BigDecimal)
        .clubDaySaveMaxMoney((map.clubDaySaveMaxMoney ?: 0) as Integer)
        .build()
  }

  static def RewardT2T3SmileCardCashbackPolicy_생성(Map map = [:]) {
    RewardT2T3SmileCardCashbackPolicy.builder()
        .policyKey((map.policyKey ?: 1L) as long)
        .cashbackAmount((map.cashbackAmount ?: 0L) as BigDecimal)
        .build()
  }
}
