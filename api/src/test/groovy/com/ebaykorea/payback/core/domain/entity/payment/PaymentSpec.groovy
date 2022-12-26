package com.ebaykorea.payback.core.domain.entity.payment

import com.ebaykorea.payback.core.domain.constant.InstallmentType
import com.ebaykorea.payback.core.exception.PaybackException
import com.ebaykorea.payback.core.exception.PaybackExceptionCode
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.PaymentGrocery.기본_Payment_생성
import static com.ebaykorea.payback.grocery.PaymentGrocery.스마일페이_Payment_생성
import static com.ebaykorea.payback.grocery.PaymentGrocery.카드_Payment_생성

class PaymentSpec extends Specification {

  def "Payment 검증"() {
    expect:
    def payment = 결제정보
    payment.getMainPaymentAmount() == 주결제금액
    payment.isManualCardPayment() == 수기결제여부
    payment.isFreeInstallment() == 무이자할부여부

    where:
    desc          | 결제정보                                                               | 주결제금액  | 수기결제여부 | 무이자할부여부
    "스마일페이"       | 스마일페이_Payment_생성()                                                 | 17000L | false  | false
    "스마일페이 무이자할부" | 스마일페이_Payment_생성(isFreeInstallment: true)                          | 17000L | false  | true
    "신용카드"        | 카드_Payment_생성()                                                    | 17000L | false  | false
    "신용카드 무이자할부"  | 카드_Payment_생성(freeInstallmentType: InstallmentType.InterestOnGmkt) | 17000L | false  | true
    "신용카드 수기결제"   | 카드_Payment_생성(isManualPayment: true)                               | 17000L | true   | false
  }

  def "Payment_불변식_검증"() {
    when:
    기본_Payment_생성()

    then:
    def e = thrown(PaybackException)
    e.code == PaybackExceptionCode.DOMAIN_ENTITY_011
  }
}
