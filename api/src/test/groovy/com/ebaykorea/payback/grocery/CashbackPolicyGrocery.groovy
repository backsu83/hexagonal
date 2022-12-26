package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.core.domain.constant.CashbackType
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.ChargePayCashbackPolicy
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.ClubDayCashbackPolicy
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.DefaultCashbackPolicy
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.ItemCashbackPolicy
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.SellerCashbackPolicy
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.SmilePayCashbackPolicy

class CashbackPolicyGrocery {
  static def SellerCashbackPolicy_생성(Map map =[:]) {
    new SellerCashbackPolicy(
        (map.policyNo ?: 0L) as long,
        (map.type ?: CashbackType.Seller) as CashbackType,
        (map.name ?: "판매자 제공 적립") as String,
        (map.subType ?: null) as String,
        (map.payType ?: "P") as String,
        (map.saveRate ?: 0L) as BigDecimal,
        (map.maxLimitMoney ?: null) as BigDecimal
    )
  }

  static def ItemCashbackPolicy_생성(Map map = [:]) {
    new ItemCashbackPolicy(
        (map.policyNo ?: 1L) as long,
        (map.type ?: CashbackType.Item) as CashbackType,
        (map.name ?: "cashbackTitle") as String,
        (map.subType ?: null) as String,
        (map.payType ?: "P") as String,
        (map.saveRate ?: 0L) as BigDecimal,
        (map.maxLimitMoney ?: 0L) as BigDecimal
    )
  }

  static def SmilePayCashbackPolicy_생성(Map map = [:]) {
    new SmilePayCashbackPolicy(
        (map.policyNo ?: 1L) as long,
        (map.type ?: CashbackType.SmilePay) as CashbackType,
        (map.name ?: "cashbackTitle") as String,
        (map.subType ?: "P") as String,
        (map.payType ?: "P") as String,
        (map.saveRate ?: 0L) as BigDecimal,
        (map.maxLimitMoney ?: 0L) as BigDecimal
    )
  }

  static def ChargePayCashbackPolicy_생성(Map map = [:]) {
    new ChargePayCashbackPolicy(
        (map.policyNo ?: 1L) as long,
        (map.type ?: CashbackType.ChargePay) as CashbackType,
        (map.name ?: "cashbackTitle") as String,
        (map.subType ?: "P") as String,
        (map.payType ?: "P") as String,
        (map.saveRate ?: 0L) as BigDecimal,
        (map.maxLimitMoney ?: 0L) as BigDecimal,
        (map.chargePaySaveRate ?: 0L) as BigDecimal,
        (map.chargePayClubSaveRate ?: 0L) as BigDecimal,
        (map.chargePayMaxMoney ?: 0L) as BigDecimal,
        (map.chargePayClubMaxMoney ?: 0L) as BigDecimal
    )
  }

  static def ClubDayCashbackPolicy_생성(Map map = [:]) {
    new ClubDayCashbackPolicy(
        (map.policyNo ?: 1L) as long,
        (map.type ?: CashbackType.ClubDay) as CashbackType,
        (map.name ?: "cashbackTitle") as String,
        (map.subType ?: "P") as String,
        (map.payType ?: "P") as String,
        (map.saveRate ?: 0L) as BigDecimal,
        (map.maxLimitMoney ?: 0L) as BigDecimal,
        (map.clubDayMaxSaveMoney ?: 0L) as BigDecimal,
        (map.clubDayMaxSaveRate ?: 0L) as BigDecimal
    )
  }

  static def DefaultCashbackPolicy_생성(Map map = [:]) {
    new DefaultCashbackPolicy(
        (map.policyNo ?: 1L) as long,
        (map.type ?: CashbackType.Unknown) as CashbackType,
        (map.name ?: "cashbackTitle") as String,
        (map.subType ?: null) as String,
        (map.payType ?: "P") as String,
        (map.saveRate ?: 0L) as BigDecimal,
        (map.maxLimitMoney ?: 0L) as BigDecimal
    )
  }
}
