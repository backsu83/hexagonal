package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class ClubDayCashbackInfoDto {
  // 클럽데이 적용일(7자리 bit string, 월화수목금토일 ex.목요일이 클럽데이인 경우 0001000)
  private String clubDay;
  // 아이템 타입(TL: 스마일 배송)
  private String itemType;
  // 클럽멤버타입(아직 미정이지만 8월에 추가 작업 예정)
  private String clubMemberType;
  // 클럽데이 캐시백 정률
  private Integer clubDayRate;
  // 클럽데이 적립상한
  private Integer clubDayMaxAmount;
  // 클럽데이 캐시백 예상 금액
  private Integer clubDayAmount;
}
