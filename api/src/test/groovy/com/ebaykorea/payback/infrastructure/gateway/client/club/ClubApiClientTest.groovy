package com.ebaykorea.payback.infrastructure.gateway.client.club

import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubBaseResponseDto
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.ClubApiGrocery.clubDataDto_생성

class ClubApiClientTest extends Specification {

    def clubApiClient = Stub(ClubApiClient)

    def "GetMemberSynopsis"() {
        setup:
        clubApiClient.getMemberSynopsis(_ as String) >> response

        expect:
        def result = clubApiClient.getMemberSynopsis(memberid);
        result == response;

        where:
        desc                | memberid    | response
        "club api 테스트"   | '103574394' | ClubBaseResponseDto.builder().data(clubDataDto_생성()).build()
    }
}
