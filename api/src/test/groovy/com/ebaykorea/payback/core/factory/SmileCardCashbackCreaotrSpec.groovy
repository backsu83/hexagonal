package com.ebaykorea.payback.core.factory

import com.ebaykorea.payback.core.domain.constant.PaymentCode
import com.ebaykorea.payback.core.domain.constant.SmileCardType
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.OrderGrocery.ItemSnapshot_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.ItemSnapshots_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.KeyMap_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.Order_생성
import static com.ebaykorea.payback.grocery.PaymentGrocery.스마일페이_Payment_생성
import static com.ebaykorea.payback.grocery.RewardGrocery.RewardCashbackPolicies_생성
import static com.ebaykorea.payback.grocery.RewardGrocery.RewardT2T3SmileCardCashbackPolicy_생성
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.SmileCardCashback_생성
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.T2T3SmileCardCashback_생성

class SmileCardCashbackCreaotrSpec extends Specification {
  def smileCardCashbackCreator = new SmileCardCashbackCreator()

  def "스마일카드 캐시백이 잘 생성되는지 확인"() {
    expect:
    def result = smileCardCashbackCreator.createSmileCardCashback(키맵, 주문, 결제, 상품, 정책)
    result.collect {
      [it.cashbackAmount, it.apply, it.toShopType(),
       it.t2t3Cashbacks.stream().collect { [it.orderNo, it.shopType, it.amount, it.basisAmount, it.smileCardType, it.apply] }]
    } == 결과.collect {
      [it.cashbackAmount, it.apply, it.toShopType(),
       it.t2t3Cashbacks.stream().collect { [it.orderNo, it.shopType, it.amount, it.basisAmount, it.smileCardType, it.apply] }]
    }

    where:
    _________________________________________________
    desc | 결과 | _ | _
    "기본" | SmileCardCashback_생성() | _ | _
    "T0" | SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true) | _ | _
    "T1" | SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true) | _ | _
    "T2_값없음" | SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true, t2t3Cashbacks: [T2T3SmileCardCashback_생성(smileCardType: SmileCardType.T2, isT2T3: true)]) | _ | _
    "T2" | SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true, t2t3Cashbacks: [T2T3SmileCardCashback_생성(amount: 100L, smileCardType: SmileCardType.T2, isT2T3: true)]) | _ | _
    _________________________________________________
    키맵 | 주문 | 결제 | 상품
    KeyMap_생성() | Order_생성() | 스마일페이_Payment_생성() | ItemSnapshots_생성(itemSnapshots: ItemSnapshot_생성())
    KeyMap_생성() | Order_생성() | 스마일페이_Payment_생성(smallCode: PaymentCode.PaymentMethodSmallCode.SmileCard) | ItemSnapshots_생성(itemSnapshots: ItemSnapshot_생성())
    KeyMap_생성() | Order_생성() | 스마일페이_Payment_생성(smallCode: PaymentCode.PaymentMethodSmallCode.SmileCardT1) | ItemSnapshots_생성(itemSnapshots: ItemSnapshot_생성())
    KeyMap_생성() | Order_생성() | 스마일페이_Payment_생성(smallCode: PaymentCode.PaymentMethodSmallCode.SmileCardT2) | ItemSnapshots_생성(itemSnapshots: ItemSnapshot_생성())
    KeyMap_생성() | Order_생성() | 스마일페이_Payment_생성(smallCode: PaymentCode.PaymentMethodSmallCode.SmileCardT2) | ItemSnapshots_생성(itemSnapshots: ItemSnapshot_생성())
    _________________________________________________
    정책 | _ | _ | _
    RewardCashbackPolicies_생성() | _ | _ | _
    RewardCashbackPolicies_생성(smileCardCashbackAmount: 1000L) | _ | _ | _
    RewardCashbackPolicies_생성(newSmileCardCashbackAmount: 1000L) | _ | _ | _
    RewardCashbackPolicies_생성(newSmileCardCashbackAmount: 1000L, smileCardCashbackPolicies: [RewardT2T3SmileCardCashbackPolicy_생성()]) | _ | _ | _
    RewardCashbackPolicies_생성(newSmileCardCashbackAmount: 1000L, smileCardCashbackPolicies: [RewardT2T3SmileCardCashbackPolicy_생성(cashbackAmount: 100L)]) | _ | _ | _
  }
}
