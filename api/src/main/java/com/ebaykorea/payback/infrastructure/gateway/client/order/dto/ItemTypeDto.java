package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

@Data
public class ItemTypeDto {
  /** 환금성 여부 */
  Boolean isMoneyCategory;

  /** 스마일배송 여부 */
  Boolean isSmileDelivery;

  /** 스마일프레시 여부 */
  Boolean isSmileFresh;
}
