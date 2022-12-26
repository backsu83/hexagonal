package com.ebaykorea.payback.core.domain.entity.payment;

import com.ebaykorea.payback.core.domain.constant.ComplexType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PaymentMethodSub {
  /**
   * 복합결제수단
   */
  ComplexType complexType;
  /**
   * 결제 금액
   */
  BigDecimal amount;
  /**
   * 중분류코드
   */
  String mediumCode;
  /**
   * 소분류코드
   */
  String smallCode;

}
