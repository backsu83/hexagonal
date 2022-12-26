package com.ebaykorea.payback.infrastructure.persistence.mapper

import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.CashbackOrderDetailEntity_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ChargePayCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ClubDayCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ItemCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SellerCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SmilePayCashback_생성
import static com.ebaykorea.payback.grocery.MemberGrocery.회원_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.Cashback_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.PayCashback_생성

class CashbackOrderDetailEntityMapperSpec extends Specification {
  def mapper = Mappers.getMapper(CashbackOrderDetailEntityMapper)

  def "CashbackOrderDetailEntityMapper가 정상적으로 변환하는지 확인"() {
    expect:
    def result = mapper.map(payCashback, cashback)
    result == expectResult

    where:
    _________________________________________________
    desc | payCashback | cashback
    "금액이 없는 경우" | PayCashback_생성() | Cashback_생성(cashbackUnits: [SellerCashback_생성()])
    "판매자캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [SellerCashback_생성(amount: 1000L)])
    "아이템캐시백_N" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ItemCashback_생성()])
    "아이템캐시백_Y" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ItemCashback_생성(isSmilePay: true)])
    "스마일페이캐시백_N" | PayCashback_생성() | Cashback_생성(cashbackUnits: [SmilePayCashback_생성()])
    "스마일페이캐시백_Y" | PayCashback_생성() | Cashback_생성(cashbackUnits: [SmilePayCashback_생성(isSmilePay: true, clubAmount: 1000L)])
    "스마일페이캐시백_Y,클럽_Y" | PayCashback_생성(member: 회원_생성(true)) | Cashback_생성(cashbackUnits: [SmilePayCashback_생성(isSmilePay: true, clubAmount: 1000L)])
    "자동충전캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ChargePayCashback_생성()])
    "자동충전캐시백,클럽" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ChargePayCashback_생성(isChargePay: true, clubAmount: 1000L)])
    "클럽데이캐시백_N" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ClubDayCashback_생성()])
    "클럽데이캐시백_Y" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ClubDayCashback_생성(isSmilePay: true, isClubMember: true)])
    _________________________________________________
    expectResult | _ | _
    CashbackOrderDetailEntity_생성() | _ | _
    CashbackOrderDetailEntity_생성(sellerAmount: 1000L) | _ | _
    CashbackOrderDetailEntity_생성(itemAmount: 1000L) | _ | _
    CashbackOrderDetailEntity_생성(itemAmount: 1000L, itemCashbackApplyYn: "Y") | _ | _
    CashbackOrderDetailEntity_생성(payAmount: 1000L) | _ | _
    CashbackOrderDetailEntity_생성(payAmount: 1000L, clubAmount: 1000L, payCashbackApplyYn: "Y") | _ | _
    CashbackOrderDetailEntity_생성(payAmount: 1000L, clubAmount: 1000L, payCashbackApplyYn: "Y", clubCashbackApplyYn: "Y") | _ | _
    CashbackOrderDetailEntity_생성(chargePayReward: 1000L) | _ | _
    CashbackOrderDetailEntity_생성(chargePayReward: 1000L, chargePayRewardClub: 1000L) | _ | _
    CashbackOrderDetailEntity_생성(clubDayAmount: 1000L) | _ | _
    CashbackOrderDetailEntity_생성(clubDayAmount: 1000L, clubDayApplyYn: "Y") | _ | _
  }
}
