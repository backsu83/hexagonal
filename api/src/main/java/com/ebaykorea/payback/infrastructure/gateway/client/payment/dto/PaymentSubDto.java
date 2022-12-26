package com.ebaykorea.payback.infrastructure.gateway.client.payment.dto;

import com.ebaykorea.payback.core.domain.constant.ComplexType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentSubDto {
    /**
     * 결제 금액
     */
    BigDecimal amount;
    /**
     * 복합결제수단
     */
    ComplexType complexType;
    /**
     * 중분류코드
     */
    String mediumCode;
    /**
     * 소분류코드
     */
    String smallCode;
}
