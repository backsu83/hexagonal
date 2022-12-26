package com.ebaykorea.payback.core.factory

import com.ebaykorea.payback.constant.TestConstant
import com.ebaykorea.payback.core.domain.constant.CashbackType
import com.ebaykorea.payback.core.factory.impl.ChargePayCashbackCreator
import com.ebaykorea.payback.core.factory.impl.ClubDayCashbackCreator
import com.ebaykorea.payback.core.factory.impl.DefaultCashbackCreator
import com.ebaykorea.payback.core.factory.impl.ItemCashbackCreator
import com.ebaykorea.payback.core.factory.impl.SellerCashbackCreator
import com.ebaykorea.payback.core.factory.impl.SmilePayCashbackCreator
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.*
import static com.ebaykorea.payback.grocery.MemberGrocery.회원_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.ItemSnapshot_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderUnitKey_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderUnit_생성
import static com.ebaykorea.payback.grocery.PaymentGrocery.스마일페이_Payment_생성
import static com.ebaykorea.payback.grocery.PaymentGrocery.카드_Payment_생성
import static com.ebaykorea.payback.grocery.RewardGrocery.RewardBackendCashbackPolicy_생성
import static com.ebaykorea.payback.grocery.RewardGrocery.RewardCashbackPolicies_생성
import static com.ebaykorea.payback.grocery.RewardGrocery.RewardCashbackPolicy_생성

class CashbackUnitFactorySpec extends Specification {
  def sellerCashbackFactory = new SellerCashbackCreator()
  def itemCashbackFactory = new ItemCashbackCreator()
  def smilePayCashbackFactory = new SmilePayCashbackCreator()
  def chargePayCashbackFactory = new ChargePayCashbackCreator()
  def clubDayCashbackFactory = new ClubDayCashbackCreator()
  def defaultCashbackFactory = new DefaultCashbackCreator()
  def cashbackFactory = new CashbackUnitFactory(
      sellerCashbackFactory,
      itemCashbackFactory,
      smilePayCashbackFactory,
      chargePayCashbackFactory,
      clubDayCashbackFactory,
      defaultCashbackFactory
  )

  def "Cashback이 정상적으로 생성되는지 확인한다"() {
    expect:
    def result = cashbackFactory.createCashbackUnits(
        TestConstant.ORDER_DATE, OrderUnitKey_생성(), OrderUnit_생성(), 회원, 결제, 상품, 복수할인금액, 리워드정책)

    result.collect { [it.itemNo, it.getCashbackType(), it.shopType, it.amount, it.basisAmount, it.useEnableDate, it.apply, it.cashbackPolicy] }
        == 결과.collect { [it.itemNo, it.getCashbackType(), it.shopType, it.amount, it.basisAmount, it.useEnableDate, it.apply, it.cashbackPolicy] }

    where:
    _________________________________________________
    desc | 결과 | _ | _
    "판매자캐시백" | [SellerCashback_생성(amount: 10L, saveRate: 1L)] | _ | _
    "판매자캐시백_복수할인" | [SellerCashback_생성(amount: 9L, basisAmount: 900L, saveRate: 1L)] | _ | _
    "판매자,아이템캐시백" | [SellerCashback_생성(), ItemCashback_생성(isSmilePay: true)] | _ | _
    "(판매자,아이템캐시백)_복수할인" | [SellerCashback_생성(basisAmount: 900L), ItemCashback_생성(basisAmount: 900L, isSmilePay: true)] | _ | _
    "판매자,아이템캐시백_false" | [SellerCashback_생성(), ItemCashback_생성(isSmilePay: false)] | _ | _
    "판매자,스마일페이캐시백" | [SellerCashback_생성(), SmilePayCashback_생성(isSmilePay: true)] | _ | _
    "판매자,스마일페이,자동충전캐시백" | [SellerCashback_생성(), SmilePayCashback_생성(isSmilePay: true), ChargePayCashback_생성(isChargePay: true)] | _ | _
    "판매자,스마일페이,자동충전캐시백_false" | [SellerCashback_생성(), SmilePayCashback_생성(isSmilePay: true), ChargePayCashback_생성(isChargePay: false)] | _ | _
    "판매자,스마일페이,자동충전,클럽데이캐시백" | [SellerCashback_생성(), SmilePayCashback_생성(isSmilePay: true), ChargePayCashback_생성(isChargePay: true), ClubDayCashback_생성(isSmilePay: true, isClubMember: true)] | _ | _
    _________________________________________________
    결제 | 상품 | 복수할인금액 | 회원
    카드_Payment_생성() | ItemSnapshot_생성(buyerMileageRate: 1L) | BigDecimal.ZERO | 회원_생성()
    카드_Payment_생성() | ItemSnapshot_생성(buyerMileageRate: 1L) | BigDecimal.valueOf(100L) | 회원_생성()
    스마일페이_Payment_생성() | ItemSnapshot_생성() | BigDecimal.ZERO | 회원_생성()
    스마일페이_Payment_생성() | ItemSnapshot_생성() | BigDecimal.valueOf(100L) | 회원_생성()
    카드_Payment_생성() | ItemSnapshot_생성() | BigDecimal.ZERO | 회원_생성()
    스마일페이_Payment_생성() | ItemSnapshot_생성() | BigDecimal.ZERO | 회원_생성()
    스마일페이_Payment_생성() | ItemSnapshot_생성() | BigDecimal.ZERO | 회원_생성()
    스마일페이_Payment_생성(smallCode: "1") | ItemSnapshot_생성() | BigDecimal.ZERO | 회원_생성()
    스마일페이_Payment_생성() | ItemSnapshot_생성() | BigDecimal.ZERO | 회원_생성(true)
    _________________________________________________
    캐시백정책 | _ | _ | _
    [] | _ | _ | _
    [] | _ | _ | _
    [RewardCashbackPolicy_생성()] | _ | _ | _
    [RewardCashbackPolicy_생성()] | _ | _ | _
    [RewardCashbackPolicy_생성()] | _ | _ | _
    [RewardCashbackPolicy_생성(cashbackCd: CashbackType.SmilePay)] | _ | _ | _
    [RewardCashbackPolicy_생성(cashbackCd: CashbackType.SmilePay), RewardCashbackPolicy_생성(cashbackCd: CashbackType.ChargePay)] | _ | _ | _
    [RewardCashbackPolicy_생성(cashbackCd: CashbackType.SmilePay), RewardCashbackPolicy_생성(cashbackCd: CashbackType.ChargePay)] | _ | _ | _
    [RewardCashbackPolicy_생성(cashbackCd: CashbackType.SmilePay), RewardCashbackPolicy_생성(cashbackCd: CashbackType.ChargePay), RewardCashbackPolicy_생성(cashbackCd: CashbackType.ClubDay)] | _ | _ | _
    _________________________________________________
    백엔드정책 | 리워드정책 | _ | _
    [] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [RewardBackendCashbackPolicy_생성()] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [RewardBackendCashbackPolicy_생성()] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [RewardBackendCashbackPolicy_생성()] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [RewardBackendCashbackPolicy_생성()] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [RewardBackendCashbackPolicy_생성(cashbackCode: CashbackType.ChargePay)] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [RewardBackendCashbackPolicy_생성(cashbackCode: CashbackType.ChargePay)] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
    [RewardBackendCashbackPolicy_생성(cashbackCode: CashbackType.ChargePay), RewardBackendCashbackPolicy_생성(cashbackCode: CashbackType.ClubDay)] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책) | _ | _
  }

  def "ChargePayCashback값이 정상적으로 생성되는지 확인한다"() {
    expect:
    def result = cashbackFactory.createCashbackUnits(
        TestConstant.ORDER_DATE, OrderUnitKey_생성(), OrderUnit_생성(), 회원_생성(), 결제, 상품, 복수할인금액, 리워드정책)

    result.stream().filter(c -> c.apply)
        .collect { [it.clubAmount] }
        == 결과.collect { [it.clubAmount] }

    where:
    _________________________________________________
    desc | 결과 | _
    "ChargePayCashback" | [ChargePayCashback_생성(isChargePay: true)] | _
    _________________________________________________
    결제 | 상품 | 복수할인금액
    스마일페이_Payment_생성() | ItemSnapshot_생성() | BigDecimal.ZERO
    _________________________________________________
    캐시백정책 | 백엔드정책 | 리워드정책
    [RewardCashbackPolicy_생성(cashbackCd: CashbackType.ChargePay)] | [RewardBackendCashbackPolicy_생성(cashbackCode: CashbackType.ChargePay)] | RewardCashbackPolicies_생성(cashbackPolicies: 캐시백정책, backendCashbackPolicies: 백엔드정책)
  }
}
