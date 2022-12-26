package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderUnitBranchDto {
  /** 지점 추가 금액 */
  private BigDecimal branchPrice;
}
