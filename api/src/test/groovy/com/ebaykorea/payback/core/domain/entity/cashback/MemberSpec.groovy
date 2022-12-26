package com.ebaykorea.payback.core.domain.entity.cashback

import com.ebaykorea.payback.core.exception.PaybackException
import com.ebaykorea.payback.core.exception.PaybackExceptionCode
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.ClubGrocery.Club_생성
import static com.ebaykorea.payback.grocery.MemberGrocery.Member_생성
import static com.ebaykorea.payback.grocery.MemberGrocery.회원_생성

class MemberSpec extends Specification {

  def "Member 검증"() {
    expect:
    def member = 회원생성
    member.hasClub() == 클럽존재여부

    where:
    desc   | 스마일클럽여부 | 클럽        | 회원생성               | 클럽존재여부
    "일반회원" | false   | null      | 회원_생성(스마일클럽여부, 클럽) | false
    "클럽회원" | false   | Club_생성() | 회원_생성(스마일클럽여부, 클럽) | true
  }

  def "Member 불변식 검증"() {
    when:
    Member_생성()

    then:
    def e = thrown(PaybackException)
    e.code == PaybackExceptionCode.DOMAIN_ENTITY_013
  }
}
