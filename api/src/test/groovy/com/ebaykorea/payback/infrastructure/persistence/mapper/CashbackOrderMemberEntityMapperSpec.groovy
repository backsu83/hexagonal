package com.ebaykorea.payback.infrastructure.persistence.mapper

import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.CashbackOrderMemberEntity_생성
import static com.ebaykorea.payback.grocery.ClubGrocery.Club_생성
import static com.ebaykorea.payback.grocery.MemberGrocery.Member_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.PayCashback_생성

class CashbackOrderMemberEntityMapperSpec extends Specification {
  def mapper = Mappers.getMapper(CashbackOrderMemberEntityMapper)

  def "CashbackOrderMemberEntityMapper가 정상적으로 변환되는지 확인"() {
    expect:
    def result = mapper.map(payCashback)
    result == expectResult

    where:
    _________________________________________________
    desc | payCashback
    "기본" | PayCashback_생성()
    "클럽정보포함" | PayCashback_생성(member: Member_생성(member: true, club: Club_생성()))
    _________________________________________________
    expectResult | _
    CashbackOrderMemberEntity_생성() | _
    CashbackOrderMemberEntity_생성(regSite: "S001", payType: "ANNL", memberGrade: "BASC", clubCheckYn: "Y") | _
  }
}
