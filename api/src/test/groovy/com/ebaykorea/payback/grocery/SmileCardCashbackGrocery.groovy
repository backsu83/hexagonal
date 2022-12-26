package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.core.domain.constant.ShopType
import com.ebaykorea.payback.core.domain.constant.SmileCardType
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.SmileCardCashback
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.T2T3SmileCardCashback

class SmileCardCashbackGrocery {
  static def SmileCardCashback_생성(Map map = [:]) {
    SmileCardCashback.of(
        (map.cashbackAmount ?: 0L) as BigDecimal,
        (map.isSmileCard ?: false) as boolean,
        (map.isFreeInstallment ?: false) as boolean,
        (map.t2t3Cashbacks ?: []) as List<T2T3SmileCardCashback>
    )
  }
  static def T2T3SmileCardCashback_생성(Map map = [:]) {
    T2T3SmileCardCashback.of(
        (map.orderNo ?: 1L) as long,
        (map.shopType ?: ShopType.Unknown) as ShopType,
        (map.amount ?: 0L) as BigDecimal,
        (map.basisAmount ?: 1000L) as BigDecimal,
        (map.smileCardType ?: SmileCardType.Unknown) as SmileCardType,
        (map.isT2T3 ?: false) as boolean,
        (map.isFreeInstallment ?: false) as boolean
    )
  }

}
