package com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth;

import lombok.Data;

@Data
public class ManualPaymentDto {
  // 카드유효년
  private String expireYear;
  // 카드유효월
  private String expireMonth;
  // 카드비번(앞두자리)
  private String partialPassword;
  // 생년월일(6자리) or 사업자등록번호(10자리)
  private String ownerRegistrationNumber;
}
