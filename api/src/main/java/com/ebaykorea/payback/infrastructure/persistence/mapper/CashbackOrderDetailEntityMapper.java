package com.ebaykorea.payback.infrastructure.persistence.mapper;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import com.ebaykorea.payback.core.domain.entity.cashback.Cashback;
import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderDetailEntity;
import com.ebaykorea.payback.util.PaybackBooleans;
import com.ebaykorea.payback.util.PaybackTimestamps;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {PaybackTimestamps.class, PaybackBooleans.class, CashbackType.class}
)
public interface CashbackOrderDetailEntityMapper {

  @Mapping(expression = "java(cashback.getCashbackAmount(CashbackType.Item))", target = "itemAmount")
  @Mapping(expression = "java(cashback.getCashbackAmount(CashbackType.Seller))", target = "sellerAmount")
  @Mapping(expression = "java(cashback.getCashbackAmount(CashbackType.SmilePay))", target = "payAmount")
  @Mapping(expression = "java(cashback.getClubAmount(CashbackType.SmilePay))", target = "clubAmount")
  @Mapping(expression = "java(PaybackBooleans.toYN(cashback.isApplyBy(CashbackType.Item)))", target = "itemCashbackApplyYn")
  @Mapping(expression = "java(PaybackBooleans.toYN(cashback.isApplyBy(CashbackType.SmilePay)))", target = "payCashbackApplyYn")
  @Mapping(source = "payCashback.member", target = "clubCashbackApplyYn")
  @Mapping(expression = "java(cashback.getNonClubAmount(CashbackType.ChargePay))", target = "chargePayReward")
  @Mapping(expression = "java(cashback.getClubAmount(CashbackType.ChargePay))", target = "chargePayRewardClub")
  @Mapping(expression = "java(cashback.getCashbackAmount(CashbackType.ClubDay))", target = "clubDayAmount")
  @Mapping(expression = "java(PaybackBooleans.toYN(cashback.isApplyBy(CashbackType.ClubDay)))", target = "clubDayApplyYn")
  @Mapping(source = "payCashback.member.buyerNo", target = "regId")
  @Mapping(source = "payCashback.member.buyerNo", target = "chgId")
  @Mapping(expression = "java(PaybackTimestamps.from(payCashback.getOrderDate()))", target = "regDt")
  @Mapping(expression = "java(PaybackTimestamps.from(payCashback.getOrderDate()))", target = "chgDt")
  CashbackOrderDetailEntity map(PayCashback payCashback, Cashback cashback);

  default String mapToSmileClubYn(final Member member) {
    return PaybackBooleans.toYN(member.isSmileClubMember());
  }
}
