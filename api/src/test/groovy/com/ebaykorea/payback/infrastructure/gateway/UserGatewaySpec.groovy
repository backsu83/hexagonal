package com.ebaykorea.payback.infrastructure.gateway

import com.ebaykorea.payback.infrastructure.gateway.client.member.QuiltApiClient
import com.ebaykorea.payback.infrastructure.gateway.client.member.dto.QuiltBaseResponse
import spock.lang.Specification

class UserGatewaySpec extends Specification {
  def client = Stub(QuiltApiClient)
  def userGateway = new UserGatewayImpl(client)

  def "userGateway 결과가 정상인지 확인"() {
    setup:
    client.findUserKey(_ as String) >> Optional.of(new QuiltBaseResponse(결과코드, "", 리턴값))

    expect:
    def result = userGateway.findUserKey("buyerNo")
    result == expectResult

    where:
    desc           | 결과코드 | 리턴값       | expectResult
    "결과코드_정상_값없음"  | 0    | ""        | Optional.of(리턴값)
    "결과코드_정상_값있음"  | 0    | "userKey" | Optional.of(리턴값)
    "결과코드_비정상_값없음" | 1    | ""        | Optional.empty()

  }
}
