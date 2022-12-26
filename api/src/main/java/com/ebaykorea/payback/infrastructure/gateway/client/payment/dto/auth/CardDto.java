package com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth;

import com.ebaykorea.payback.core.domain.constant.InstallmentType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class CardDto {
  private InstallmentType freeInstallmentType;
  private ManualPaymentDto manualPayment;

}
