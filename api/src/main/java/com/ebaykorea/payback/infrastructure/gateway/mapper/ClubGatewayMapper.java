package com.ebaykorea.payback.infrastructure.gateway.mapper;

import com.ebaykorea.payback.core.domain.entity.cashback.member.Club;
import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClubGatewayMapper {

    @Mapping(source = "member.membershipGrade", target = "membershipGrade")
    @Mapping(source = "member.payCycleType", target = "payCycleType")
    @Mapping(source = "member.partnerId", target = "partnerId")
    Club map(ClubDataDto clubDataDto);
}
