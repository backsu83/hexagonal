package com.ebaykorea.payback.core.domain.entity.order;

import com.ebaykorea.payback.core.domain.constant.ShopType;
import lombok.*;

import java.math.BigDecimal;

@Value
@Builder
public class ItemSnapshot {
  /** 상품 스냅샷 키 */
  String snapshotKey;

  /** 상품번호 */
  String itemNo;

  /** 판매자고객번호 */
  String sellerCustNo;

  /** 상품대분류코드 */
  String itemLargeCategoryCode;

  /** 상품중분류코드 */
  String itemMediumCategoryCode;

  /** 상품소분류코드 */
  String itemSmallCategoryCode;

  /** 환금성 상품 여부 */
  boolean isMoneyCategory;

  /** 스마일배송 상품 여부 */
  boolean isSmileDelivery;

  /** 스마일프레시 상품 여부 */
  boolean isSmileFresh;

  /** 구매자마일리지율 */
  BigDecimal buyerMileageRate;

  public ShopType toShopType() {
    if (isSmileDelivery) {
      return ShopType.SmileDelivery;
    } else if (isSmileFresh) {
      return ShopType.SmileFresh;
    } else {
      return ShopType.Unknown;
    }
  }
}
