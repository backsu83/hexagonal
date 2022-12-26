package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CashbackInfoDto {
  /** 캐시백 코드 */
  private CashbackType cashbackCd;
  /** 캐시백금액 */
  private Integer cashbackAmount;
  /** 캐시백 정책번호 */
  private Long cashbackSeq;
  /** 적립타입 P, W */
  private String payType;
  /** 적립율 */
  private BigDecimal payRate;
  /** 최대적립가능금액 */
  private BigDecimal payMaxMoney;
  /** 캐시백타이틀 */
  private String cashbackTitle;
  /** 캐시백 기타 입력사항 타이틀(G9) */
  private String etcTitle;
  /** 캐시백 기타 입력사항 내용(G9) */
  private String etcContent;
}
