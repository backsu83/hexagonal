package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderItemDto {
  /** 상품 snapshot key */
  private String snapshotKey;

  /** 상품 번호 */
  private String itemNo;

  /** 상품 판매 가격 */
  private BigDecimal basePrice;

  /** 주문수량, 참고로 주문옵션(orderOptions) 도 이 수량을 따라간다. */
  private int quantity;

  /** 주문 옵션 */
  private List<OrderItemOptionDto> options;

  /** 추가 구성 */
  private List<OrderItemAdditionDto> additions;

  /** 지점 정보 */
  private OrderUnitBranchDto branch;
}
