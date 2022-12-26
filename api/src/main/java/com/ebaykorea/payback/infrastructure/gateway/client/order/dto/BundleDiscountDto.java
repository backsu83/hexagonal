package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BundleDiscountDto {
  /** 복수 구매 할인 Snapshot 키 */
  private String snapshotKey;

  /** orderUnit 별로 적용된 bundle Discount 상세 */
  private List<BundleDiscountUnitDto> bundleDiscountUnits;

  @Data
  public static class BundleDiscountUnitDto {
    /** 복수 구매 할인 적용된 주문 단위 키 */
    private String orderUnitKey;

    /** 복수 구매 할인 금액 */
    private BigDecimal discountAmount;
  }
}
