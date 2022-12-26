package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CashbackRewardBackendResponseDto {
  //Unique Key - buyOrderNo
  private String key;

  //상품번호
  private String gdNo;

  //캐시백 정책번호
  private Integer cashbackSeq;

  //캐시백 코드 ( 1 = ItemCashback, 2 = NewSmilePayCashback, 3 = SellerCashback, 4 = CartCashback )
  private CashbackType cashbackCode;

  //캐시백 타이틀
  private String cashbackTitle;

  //사용가능일 "2022-04-22"
  private String useEnableDate;

  //캐시백 금액
  private Integer cashbackMoney;

  //지급타입 (1 = FixedRate, 2 = FixedAmount )
  private Integer payType;

  //지급 정액/률
  private BigDecimal payRate;

  //지급 최대 금액
  private Integer payMaxMoney;

  //자동충전결제 지급률
  private BigDecimal chargePayRewardRate;

  //자동충전결제 클럽회원 지급률
  private BigDecimal chargePayRewardClubRate;

  //자동충전결제 스마일배송 지급률
  private Integer chargePayRewardSmileDeliveryRate;

  //자동충전결제 스마일배송 클럽회원 지급률
  private Integer chargePayRewardSmileDeliveryClubRate;

  //자동충전결제 지급최대금액
  private Integer chargePayRewardMaxMoney;

  //자동충전결제 클럽회원 지급최대금액
  private Integer chargePayRewardClubMaxMoney;

  //자동충전결제 지급예상금액
  private Integer chargeCashbackMoney;

  //자동충전결제 클럽회원 지급예상금액
  private Integer chargeClubCashbackMoney;

  //클럽데이 적용일(7자리 bit string, 월화수목금토일 ex.목요일이 클럽데이인 경우 0001000)
  private String clubDay;

  //클럽데이 지급률
  private BigDecimal clubDayPayRate;

  //클럽데이 지급최대금액
  private Integer clubDaySaveMaxMoney;

  //클럽데이 지급예상금액
  private Integer clubDayCashbackMoney;
}
