package com.ebaykorea.payback.infrastructure.persistence.mapper

import com.ebaykorea.payback.core.domain.constant.ShopType
import com.ebaykorea.payback.core.domain.constant.SmileCardType
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.PayCashbackGrocery.PayCashback_생성
import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.SmilecardCashbackOrderEntity_생성
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.SmileCardCashback_생성
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.T2T3SmileCardCashback_생성

class SmilecardCashbackOrderEntityMapperSpec extends Specification {
    def mapper = Mappers.getMapper(SmilecardCashbackOrderEntityMapper)

    def "SmilecardCashbackOrderEntityMapper가 정상적으로 변환되는지 확인"() {
        expect:
        def result = mapper.map(payCashback , smileCardCashback)
        result == expectResult

        where:
        _________________________________________________
        desc | payCashback | smileCardCashback
        "기본" | PayCashback_생성() | SmileCardCashback_생성(cashbackAmount: 1000L)
        "T2,T3 금액" | PayCashback_생성() | SmileCardCashback_생성(cashbackAmount: 1000L, t2t3Cashbacks:[T2T3SmileCardCashback_생성(amount: 1000L), T2T3SmileCardCashback_생성(amount: 1000L)])
        "배송타입 SD" | PayCashback_생성() | SmileCardCashback_생성(cashbackAmount: 1000L, t2t3Cashbacks:[T2T3SmileCardCashback_생성(amount: 1000L, shopType: ShopType.SmileDelivery)])
        "배송타입 SF" | PayCashback_생성() | SmileCardCashback_생성(cashbackAmount: 1000L, t2t3Cashbacks:[T2T3SmileCardCashback_생성(amount: 1000L, shopType: ShopType.SmileFresh)])
        "T0,T1" | PayCashback_생성() | SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true,t2t3Cashbacks:[T2T3SmileCardCashback_생성(amount: 1000L, smileCardType: SmileCardType.Unknown, isT2T3: false)])
        "T2,T3" | PayCashback_생성() | SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true, t2t3Cashbacks:[T2T3SmileCardCashback_생성(amount: 1000L, smileCardType: SmileCardType.T2, isT2T3: true)])
        _________________________________________________
        expectResult | _ | _
        SmilecardCashbackOrderEntity_생성() | _ | _
        SmilecardCashbackOrderEntity_생성(t2t3CashbackAmount: 2000L) | _ | _
        SmilecardCashbackOrderEntity_생성(t2t3CashbackAmount: 1000L , itemType: "SD") | _ | _
        SmilecardCashbackOrderEntity_생성(t2t3CashbackAmount: 1000L , itemType: "SF") | _ | _
        SmilecardCashbackOrderEntity_생성(t2t3CashbackAmount: 1000L , applyYn: "Y" , t2t3ApplyYn: "N") | _ | _
        SmilecardCashbackOrderEntity_생성(t2t3CashbackAmount: 1000L , applyYn: "Y" , t2t3ApplyYn: "Y") | _ | _
    }
}
