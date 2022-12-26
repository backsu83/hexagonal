package com.ebaykorea.payback.infrastructure.gateway

import com.ebaykorea.payback.infrastructure.gateway.client.club.ClubApiClient
import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubBaseResponseDto
import com.ebaykorea.payback.infrastructure.gateway.mapper.ClubGatewayMapper
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.ClubApiGrocery.clubDataDto_생성
import static com.ebaykorea.payback.grocery.ClubGrocery.Club_생성

class ClubGatewaySpec extends Specification {
    def clubApiClient = Stub(ClubApiClient)
    def clubGatewayMapper = Mappers.getMapper(ClubGatewayMapper)
    def clubGatewayImpl = new ClubGatewayImpl(clubApiClient , clubGatewayMapper)

    def "Club 변환 확인"() {
        setup:
        clubApiClient.getMemberSynopsis(_ as String) >> ClubBaseResponseDto.builder().message("Success").data(response).build()

        expect:
        def result = clubGatewayImpl.findMemberSynopsis("custNo")
        result == expectResult

        where:
        desc | response | expectResult
        "Dto 변환"  | clubDataDto_생성() | Optional.of(Club_생성())
    }
}
