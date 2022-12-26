package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class ItemCashbackInfoDto {
  private Integer itemAmount;
  /** 셀러 캐시백 */
  private Integer sellerAmount;
  /** 카테고리 캐시백 */
  private Integer categoryAmount;
  /** 셀러 + 카테고리 캐시백 */
  private Integer sellerCategoryAmount;
  /** 셀러 캐시백 */
  private Integer shopAmount;
  /** 기타 캐시백 (원래 기타캐시백은 있을 수 없지만 차추 확인을 위하여 존재) */
  private Integer etcAmount;
}
