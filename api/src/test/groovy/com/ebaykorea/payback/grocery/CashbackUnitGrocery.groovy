package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.constant.TestConstant
import com.ebaykorea.payback.core.domain.constant.CashbackType
import com.ebaykorea.payback.core.domain.constant.ShopType
import com.ebaykorea.payback.core.domain.entity.cashback.unit.ChargePayCashback
import com.ebaykorea.payback.core.domain.entity.cashback.unit.ClubDayCashback
import com.ebaykorea.payback.core.domain.entity.cashback.unit.DefaultCashback
import com.ebaykorea.payback.core.domain.entity.cashback.unit.ItemCashback
import com.ebaykorea.payback.core.domain.entity.cashback.unit.SellerCashback
import com.ebaykorea.payback.core.domain.entity.cashback.unit.SmilePayCashback
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy

import java.time.Instant

import static com.ebaykorea.payback.grocery.CashbackPolicyGrocery.ChargePayCashbackPolicy_생성
import static com.ebaykorea.payback.grocery.CashbackPolicyGrocery.ClubDayCashbackPolicy_생성
import static com.ebaykorea.payback.grocery.CashbackPolicyGrocery.DefaultCashbackPolicy_생성
import static com.ebaykorea.payback.grocery.CashbackPolicyGrocery.ItemCashbackPolicy_생성
import static com.ebaykorea.payback.grocery.CashbackPolicyGrocery.SellerCashbackPolicy_생성
import static com.ebaykorea.payback.grocery.CashbackPolicyGrocery.SmilePayCashbackPolicy_생성

class CashbackUnitGrocery {
  static def SellerCashback_생성(Map map = [:]) {
    new SellerCashback(
        (map.itemNo ?: "itemNo1") as String,
        (map.shopType ?: ShopType.Unknown) as ShopType,
        (map.amount ?: 0L) as BigDecimal,
        (map.basisAmount ?: 1000L) as BigDecimal,
        (map.useEnableDate ?: TestConstant.ORDER_DATE_ADD_TO_30_DAYS) as Instant,
        (map.cashbackPolicy ?: SellerCashbackPolicy_생성(map)) as CashbackPolicy
    )
  }

  static def ItemCashback_생성(Map map = [:]) {
    new ItemCashback(
        (map.itemNo ?: "itemNo1") as String,
        (map.shopType ?: ShopType.Unknown) as ShopType,
        (map.amount ?: 1000L) as BigDecimal,
        (map.basisAmount ?: 1000L) as BigDecimal,
        (map.useEnableDate ?: TestConstant.USE_ENABLE_DATE) as Instant,
        (map.isSmilePay) as boolean,
        (map.cashbackPolicy ?: ItemCashbackPolicy_생성(map)) as CashbackPolicy
    )
  }

  static def SmilePayCashback_생성(Map map = [:]) {
    new SmilePayCashback(
        (map.itemNo ?: "itemNo1") as String,
        (map.shopType ?: ShopType.Unknown) as ShopType,
        (map.amount ?: 1000L) as BigDecimal,
        (map.basisAmount ?: 1000L) as BigDecimal,
        (map.useEnableDate ?: TestConstant.USE_ENABLE_DATE) as Instant,
        (map.isSmilePay) as boolean,
        (map.cashbackPolicy ?: SmilePayCashbackPolicy_생성(map)) as CashbackPolicy,
        (map.clubAmount ?: 0L) as BigDecimal
    )
  }

  static def ChargePayCashback_생성(Map map = [:]) {
    new ChargePayCashback(
        (map.itemNo ?: "itemNo1") as String,
        (map.shopType ?: ShopType.Unknown) as ShopType,
        (map.amount ?: 1000L) as BigDecimal,
        (map.basisAmount ?: 1000L) as BigDecimal,
        (map.useEnableDate ?: TestConstant.USE_ENABLE_DATE) as Instant,
        (map.clubAmount ?: 0L) as BigDecimal,
        (map.nonClubAmount ?: 1000L) as BigDecimal,
        (map.isChargePay) as boolean,
        (map.cashbackPolicy ?: ChargePayCashbackPolicy_생성(map)) as CashbackPolicy
    )
  }

  static def ClubDayCashback_생성(Map map = [:]) {
    new ClubDayCashback(
        (map.itemNo ?: "itemNo1") as String,
        (map.shopType ?: ShopType.Unknown) as ShopType,
        (map.amount ?: 1000L) as BigDecimal,
        (map.basisAmount ?: 1000L) as BigDecimal,
        (map.useEnableDate ?: TestConstant.USE_ENABLE_DATE) as Instant,
        (map.isSmilePay) as boolean,
        (map.isClubMember) as boolean,
        (map.cashbackPolicy ?: ClubDayCashbackPolicy_생성(map)) as CashbackPolicy
    )
  }

  static def DefaultCashback_생성(Map map = [:]) {
    new DefaultCashback(
        (map.itemNo ?: "itemNo1") as String,
        (map.type ?: CashbackType.Unknown) as CashbackType,
        (map.shopType ?: ShopType.Unknown) as ShopType,
        (map.amount ?: 1000L) as BigDecimal,
        (map.basisAmount ?: 1000L) as BigDecimal,
        (map.useEnableDate ?: TestConstant.USE_ENABLE_DATE) as Instant,
        (map.cashbackPolicy ?: DefaultCashbackPolicy_생성(map)) as CashbackPolicy
    )
  }
}
