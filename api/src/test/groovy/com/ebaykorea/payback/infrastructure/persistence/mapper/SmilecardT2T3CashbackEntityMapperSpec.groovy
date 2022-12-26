package com.ebaykorea.payback.infrastructure.persistence.mapper

import com.ebaykorea.payback.core.domain.constant.OrderSiteType
import com.ebaykorea.payback.core.domain.constant.SmileCardType
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.SmilecardT2T3CashbackEntity_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.*
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.T2T3SmileCardCashback_생성

class SmilecardT2T3CashbackEntityMapperSpec extends Specification {
  def mapper = Mappers.getMapper(SmilecardT2T3CashbackEntityMapper)

  def "SmilecardT2T3CashbackEntityMapper가 정상적으로 변환되는지 확인"() {
    expect:
    def result = mapper.map(payCashback , t2t3SmileCardCashback)
    result == expectResult

    where:
    _________________________________________________
    desc | payCashback | t2t3SmileCardCashback
    "T2,T3" | PayCashback_생성() | T2T3SmileCardCashback_생성(amount: 1000L, smileCardType: SmileCardType.T2, isT2T3: true)
    _________________________________________________
    expectResult | _ | _
    SmilecardT2T3CashbackEntity_생성(t2t3CashbackAmount: 1000L , smileCardType: "T2" , siteCode: OrderSiteType.Gmarket.shortCode) | _ | _
  }
}
