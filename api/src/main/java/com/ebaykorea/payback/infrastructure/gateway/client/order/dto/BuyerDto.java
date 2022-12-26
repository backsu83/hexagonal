package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import com.ebaykorea.payback.core.domain.constant.MemberType;
import lombok.Data;

@Data
public class BuyerDto {
  /** 구매자 아이디 */
  private String buyerId;

  /** 구매자 번호 */
  private String buyerNo;

  /** 구매자 타입 */
  private MemberType memberType;

  /** 스마일 클럽 정보 */
  private SmileClubMembershipDto smileClubMembership;

  @Data
  public static class SmileClubMembershipDto {
    private boolean smileClubMember;
  }
}
