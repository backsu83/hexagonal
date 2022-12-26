package com.ebaykorea.payback.infrastructure.persistence.mapper;

import com.ebaykorea.payback.core.domain.entity.cashback.Cashback;
import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderPolicyEntity;

public interface CashbackPolicyEntityMapper {

  CashbackOrderPolicyEntity map(PayCashback payCashback, Cashback cashback, CashbackPolicy policy);
}
