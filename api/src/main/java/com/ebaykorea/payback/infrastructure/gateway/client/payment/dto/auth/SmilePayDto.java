package com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmilePayDto {
  private String certificationId;
  private String smilePayToken;
  private BigDecimal totalMoney;
  private BigDecimal cardRequestMoney;
  private BigDecimal cashRequestMoney;
  private BigDecimal mobileRequestMoney;
  private BigDecimal etcRequestMoney;
  @JsonProperty("ePrepayRequestMoney")
  private BigDecimal prepayRequestMoney;
  private Boolean isFreeInstallment;
  private Long settleGroupSequence;
  private List<Long> smilePayContractCode;
  private Integer smilePayItemType;
}
