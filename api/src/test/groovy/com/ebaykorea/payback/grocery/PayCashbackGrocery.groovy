package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.constant.TestConstant
import com.ebaykorea.payback.core.domain.constant.OrderSiteType
import com.ebaykorea.payback.core.domain.constant.ShopType
import com.ebaykorea.payback.core.domain.constant.SmileCardType
import com.ebaykorea.payback.core.domain.entity.cashback.Cashback
import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback
import com.ebaykorea.payback.core.domain.entity.cashback.member.Member
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.SmileCardCashback
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.T2T3SmileCardCashback
import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit

import java.time.Instant

import static com.ebaykorea.payback.grocery.MemberGrocery.회원_생성

class PayCashbackGrocery {
  static def PayCashback_생성(Map map = [:]) {
    PayCashback.of(
        (map.packNo ?: 1L) as long,
        (map.orderDate ?: TestConstant.ORDER_DATE) as Instant,
        (map.member ?: 회원_생성()) as Member,
        (map.cashbacks ?: []) as List<Cashback>,
        (map.smileCardCashback ?: null) as SmileCardCashback
    )
  }

  static def Cashback_생성(Map map = [:]) {
    Cashback.of(
        (map.orderNo ?: 1L) as long,
        (map.cashbackUnits ?: []) as List<CashbackUnit>
    )
  }
}
