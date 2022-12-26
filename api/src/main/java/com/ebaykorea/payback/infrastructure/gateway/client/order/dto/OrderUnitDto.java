package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderUnitDto {
  /** 주문 단위 Key */
  private String orderUnitKey;

  /** 주문 Item 정보 */
  private OrderItemDto orderItem;

  /** 상품 할인 정보 */
  private List<OrderUnitDiscountDto> itemDiscounts;

  /** 쿠폰 정보 */
  private List<OrderUnitCouponDto> coupons;
}
