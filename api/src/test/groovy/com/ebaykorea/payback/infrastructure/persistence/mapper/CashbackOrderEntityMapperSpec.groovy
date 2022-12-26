package com.ebaykorea.payback.infrastructure.persistence.mapper

import com.ebaykorea.payback.constant.TestConstant
import com.ebaykorea.payback.core.domain.constant.ShopType
import com.ebaykorea.payback.util.PaybackTimestamps
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.CashbackOrderEntity_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ChargePayCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ClubDayCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ItemCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SellerCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SmilePayCashback_생성
import static com.ebaykorea.payback.grocery.MemberGrocery.회원_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.Cashback_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.PayCashback_생성

class CashbackOrderEntityMapperSpec extends Specification {
  def mapper = Mappers.getMapper(CashbackOrderEntityMapper)

  def "CashbackOrderEntityMapper가 정상인지 확인"() {

    expect:
    def result = mapper.map(payCashback, cashback, cashback.findAppliedCashbackUnits())
    result == expectResult

    where:
    _________________________________________________
    desc | payCashback | cashback
    "적용대상이 아닌 경우 미생성" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ItemCashback_생성(), SmilePayCashback_생성(), ChargePayCashback_생성(), ClubDayCashback_생성()])
    "아이템캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ItemCashback_생성(isSmilePay: true)])
    "판매자캐시백+userkey+smileClub" | PayCashback_생성(member: 회원_생성(true, null, "userKey")) | Cashback_생성(cashbackUnits: [SellerCashback_생성(amount: 1000L)])
    "스마일페이캐시백+shopType:SD" | PayCashback_생성() | Cashback_생성(cashbackUnits: [SmilePayCashback_생성(isSmilePay: true, shopType: ShopType.SmileDelivery)])
    "자동충전캐시백+shopType:SF" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ChargePayCashback_생성(isChargePay: true, shopType: ShopType.SmileFresh)])
    "클럽데이캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ClubDayCashback_생성(isSmilePay: true, isClubMember: true)])
    _________________________________________________
    expectResult | _ | _
    [] | _ | _
    [CashbackOrderEntity_생성()] | _ | _
    [CashbackOrderEntity_생성(cashbackType: "S", userKey: "userKey", smileClubYn: "Y", useEnableDt: PaybackTimestamps.from(TestConstant.ORDER_DATE_ADD_TO_30_DAYS))] | _ | _
    [CashbackOrderEntity_생성(cashbackType: "P", shopType: "SD")] | _ | _
    [CashbackOrderEntity_생성(cashbackType: "A", shopType: "SF")] | _ | _
    [CashbackOrderEntity_생성(cashbackType: "D")] | _ | _

  }
}
