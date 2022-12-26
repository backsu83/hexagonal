package com.ebaykorea.payback.core.service

import com.ebaykorea.payback.core.gateway.ClubGateway
import com.ebaykorea.payback.core.gateway.UserGateway
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.ClubGrocery.Club_생성
import static com.ebaykorea.payback.grocery.MemberGrocery.Member_생성
import static com.ebaykorea.payback.grocery.MemberGrocery.회원_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.Buyer_생성

class MemberServiceSpec extends Specification {
  def clubGateway = Stub(ClubGateway)
  def userGateway = Stub(UserGateway)
  def service = new MemberService(clubGateway, userGateway)

  def "Member가 정상적으로 생성되는지 확인"() {
    setup:
    clubGateway.findMemberSynopsis(_ as String) >> Optional.ofNullable(클럽결과)
    userGateway.findUserKey(_ as String) >> Optional.ofNullable(유저키결과)

    expect:
    def result = service.getMember(구매자)
    result == expectResult

    where:
    desc      | 클럽결과      | 유저키결과     | 구매자                         | expectResult
    "기본"      | null      | null      | Buyer_생성(member: true) | 회원_생성()
    "클럽"      | Club_생성() | "" | Buyer_생성(member: true) | 회원_생성(false, 클럽결과)
    "userKey" | null      | "userKey" | Buyer_생성(member: true) | Member_생성(userKey: "userKey", member: true)
  }
}
