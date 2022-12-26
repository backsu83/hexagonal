package com.ebaykorea.payback.infrastructure.gateway.client.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMainDto {
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
