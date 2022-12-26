package com.ebaykorea.payback.infrastructure.persistence.mapper;


import com.ebaykorea.payback.core.domain.entity.cashback.Cashback;
import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.*;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderPolicyEntity;
import com.ebaykorea.payback.util.PaybackTimestamps;
import com.ebaykorea.payback.util.support.Conditioner;
import com.ebaykorea.payback.util.support.Precondition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = PaybackTimestamps.class
)
@Precondition(SellerCashbackPolicy.class)
@Precondition(ItemCashbackPolicy.class)
@Precondition(SmilePayCashbackPolicy.class)
@Precondition(DefaultCashbackPolicy.class)
public abstract class DefaultCashbackPolicyEntityMapper implements CashbackPolicyEntityMapper{

  @Override
  public CashbackOrderPolicyEntity map(final PayCashback payCashback, final Cashback cashback, final CashbackPolicy policy) {
    return mapToPolicy(payCashback, cashback, policy);
  }

  @Mapping(source = "policy.type.dbCode", target = "type")
  @Mapping(source = "payCashback.member.buyerNo", target = "regId")
  @Mapping(expression = "java(PaybackTimestamps.from(payCashback.getOrderDate()))", target = "regDt")
  abstract CashbackOrderPolicyEntity mapToPolicy(PayCashback payCashback, Cashback cashback, CashbackPolicy policy);
}
