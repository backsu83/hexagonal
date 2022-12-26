package com.ebaykorea.payback.infrastructure.gateway.client.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    /** 결제 순번 **/
    Long paymentSequence;
    /** 거래 키 **/
    String txKey;
    /** 구매자 회원 번호 **/
    String buyerNo;
    /** 구매자 회원 아이디 **/
    String buyerId;
    /** 제휴사 코드 **/
    String partnershipCode;
    /** main 결제 수단 */
    PaymentMainDto mainPaymentMethod;
    /** sub 결제 수단 */
    List<PaymentSubDto> subPaymentMethods;
    /** 인증 정보 */
    PaymentAuthDto authentications;

}
