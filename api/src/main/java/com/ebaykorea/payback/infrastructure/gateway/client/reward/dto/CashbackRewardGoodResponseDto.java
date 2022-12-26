package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CashbackRewardGoodResponseDto {
  /** 클럽데이 예상 금액(클럽데이 적용될 경우에만 값이 있고 아니면 0) */
  private Integer clubDayExpectSaveAmount;
  /** 클럽데이 예상 적립률(클럽데이 적용될 경우에만 값이 있고 아니면 0) */
  private Integer clubDayExpectSaveRate;
  /** Unique Key */
  private String key;
  /** 상품번호 */
  private String gdNo;
  /** 스마일클럽가입시 캐시백 */
  private Integer ifSmileClubCashbackAmount;
  /** 캐시백 정보 */
  private List<CashbackInfoDto> cashbackInfo;
  /** 아이템 캐시백정보 */
  private ItemCashbackInfoDto  itemCashbackInfo;
  /** 스마일페이 캐시백 정보 */
  private NspCashbackInfoDto NSPCashbackInfo;
  /** 스마일카드 T2T3 일때 금액 **/
  private Integer ifSmileCardT2T3CashbackAmount;
  /** 클럽데이 캐시백 **/
  private ClubDayCashbackInfoDto clubDayCashbackInfo;
}
