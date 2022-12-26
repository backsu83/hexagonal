package com.ebaykorea.payback.infrastructure.persistence.mapper;

import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.member.Club;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderMemberEntity;
import com.ebaykorea.payback.util.PaybackBooleans;
import com.ebaykorea.payback.util.PaybackTimestamps;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {PaybackTimestamps.class}
)
public interface CashbackOrderMemberEntityMapper {

  @Mapping(source = "member.buyerNo", target = "buyerNo")
  @Mapping(source = "member.club.partnerId", target = "regSite")
  @Mapping(source = "member.club.payCycleType", target = "payType")
  @Mapping(source = "member.club.membershipGrade", target = "memberGrade")
  @Mapping(source = "member.club", target = "clubCheckYn")
  @Mapping(source = "member.buyerNo", target = "insOprt")
  @Mapping(expression = "java(PaybackTimestamps.from(payCashback.getOrderDate()))", target = "insDate")
  @Mapping(source = "member.buyerNo", target = "updOprt")
  @Mapping(expression = "java(PaybackTimestamps.from(payCashback.getOrderDate()))", target = "updDate")
  CashbackOrderMemberEntity map(PayCashback payCashback);

  default String mapIsClub(final Club club) {
    return PaybackBooleans.toYN(Optional.ofNullable(club).isPresent());
  }
}
