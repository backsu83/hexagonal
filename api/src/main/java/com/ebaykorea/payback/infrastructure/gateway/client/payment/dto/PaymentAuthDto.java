package com.ebaykorea.payback.infrastructure.gateway.client.payment.dto;

import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth.CardDto;
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth.SmilePayDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAuthDto {
  SmilePayDto smilePay;
  CardDto card;
}
