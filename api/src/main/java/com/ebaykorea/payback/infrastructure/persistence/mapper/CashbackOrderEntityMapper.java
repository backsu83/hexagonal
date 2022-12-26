package com.ebaykorea.payback.infrastructure.persistence.mapper;

import com.ebaykorea.payback.core.domain.entity.cashback.Cashback;
import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderEntity;
import java.util.List;
import java.util.stream.Collectors;

import com.ebaykorea.payback.util.PaybackBooleans;
import com.ebaykorea.payback.util.PaybackTimestamps;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {PaybackTimestamps.class}
)
public interface CashbackOrderEntityMapper {

  default List<CashbackOrderEntity> map(final PayCashback payCashback, final Cashback cashback, final List<CashbackUnit> cashbackUnits) {
    return cashbackUnits.stream()
        .map(cashbackUnit -> map(payCashback, cashback, cashbackUnit))
        .collect(Collectors.toUnmodifiableList());
  }

  @Mapping(expression = "java(cashbackUnit.getCashbackType().getDbCode())", target = "cashbackType")
  @Mapping(source = "payCashback.member.buyerNo", target = "buyerNo")
  @Mapping(source = "payCashback.member.userKey", target = "userKey")
  @Mapping(expression = "java(PaybackTimestamps.from(cashbackUnit.getUseEnableDate()))", target = "useEnableDt")
  @Mapping(source = "payCashback.member", target = "smileClubYn")
  @Mapping(source = "cashbackUnit.shopType.code", target = "shopType")
  @Mapping(source = "payCashback.member.buyerNo", target = "regId")
  @Mapping(expression = "java(PaybackTimestamps.from(payCashback.getOrderDate()))", target = "regDt")
  @Mapping(source = "payCashback.member.buyerNo", target = "chgId")
  @Mapping(constant = "SV", target = "tradeCd")
  @Mapping(constant = "10", target = "tradeStatus")
  @Mapping(constant = "G", target = "siteType")
  CashbackOrderEntity map(PayCashback payCashback, Cashback cashback, CashbackUnit cashbackUnit);

  default String mapToSmileClubYn(final Member member) {
    return PaybackBooleans.toYN(member.isSmileClubMember());
  }
}
