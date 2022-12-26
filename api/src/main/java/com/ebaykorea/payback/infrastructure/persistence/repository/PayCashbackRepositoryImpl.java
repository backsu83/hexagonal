package com.ebaykorea.payback.infrastructure.persistence.repository;

import com.ebaykorea.payback.core.domain.entity.cashback.Cashback;
import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.SmileCardCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.T2T3SmileCardCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.order.KeyMap;
import com.ebaykorea.payback.core.domain.entity.order.OrderUnitKey;
import com.ebaykorea.payback.core.repository.PayCashbackRepository;
import com.ebaykorea.payback.infrastructure.persistence.mapper.CashbackOrderDetailEntityMapper;
import com.ebaykorea.payback.infrastructure.persistence.mapper.CashbackOrderEntityMapper;
import com.ebaykorea.payback.infrastructure.persistence.mapper.CashbackOrderMemberEntityMapper;
import com.ebaykorea.payback.infrastructure.persistence.mapper.CashbackPolicyEntityMapper;
import com.ebaykorea.payback.infrastructure.persistence.mapper.SmilecardCashbackOrderEntityMapper;
import com.ebaykorea.payback.infrastructure.persistence.mapper.SmilecardT2T3CashbackEntityMapper;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderDetailRepository;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderMemberRepository;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderPolicyRepository;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderRepository;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.SmilecardCashbackOrderRepository;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.SmilecardT2T3CashbackRepository;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderPolicyEntity;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.SmilecardT2T3CashbackEntity;
import com.ebaykorea.payback.util.support.Conditioner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayCashbackRepositoryImpl implements PayCashbackRepository {

  private final CashbackOrderRepository cashbackOrderRepository;
  private final CashbackOrderPolicyRepository cashbackOrderPolicyRepository;
  private final CashbackOrderDetailRepository cashbackOrderDetailRepository;
  private final CashbackOrderMemberRepository cashbackOrderMemberRepository;
  private final SmilecardCashbackOrderRepository smilecardCashbackOrderRepository;
  private final SmilecardT2T3CashbackRepository smilecardT2T3CashbackRepository;

  private final CashbackOrderEntityMapper cashbackOrderEntityMapper;
  private final Conditioner<CashbackPolicyEntityMapper> cashbackPolicyEntityMapperConditioner;
  private final CashbackOrderDetailEntityMapper cashbackOrderDetailEntityMapper;
  private final CashbackOrderMemberEntityMapper cashbackOrderMemberEntityMapper;
  private final SmilecardCashbackOrderEntityMapper smilecardCashbackOrderEntityMapper;
  private final SmilecardT2T3CashbackEntityMapper smilecardT2T3CashbackEntityMapper;

  @Transactional
  @Override
  public void save(final PayCashback payCashback) {
    payCashback.getCashbacks()
        .forEach(cashback -> {
          //cashback_order
          saveCashbackUnits(payCashback, cashback, cashback.findAppliedCashbackUnits());

          //cashback_order_policy
          saveCashbackPolicies(payCashback, cashback, cashback.findAppliedCashbackPolicies());

          //cashback_order_detail
          saveCashbackDetail(payCashback, cashback);
        });

    //cashback_order_member
    saveCashbackMember(payCashback);

    if (payCashback.hasSmileCardCashback()) {
      //smilecard_cash
      saveSmileCardCashback(payCashback, payCashback.getSmileCardCashback());
      //smilecard_t2t3_cashback_info
      saveSmileCardT2T3Cashback(payCashback, payCashback.getSmileCardCashback());
    }
  }

  @Override
  public boolean isDuplicatedCashback(KeyMap keyMap) {
    return keyMap.getOrderUnitKeys().stream()
        .map(OrderUnitKey::getBuyOrderNo)
        .map(cashbackOrderDetailRepository::findById)
        .anyMatch(Optional::isPresent);
  }

  private void saveCashbackUnits(final PayCashback payCashback, final Cashback cashback, final List<CashbackUnit> cashbackUnits) {
    final var cashbackOrderEntities = cashbackOrderEntityMapper.map(payCashback, cashback, cashbackUnits);
    cashbackOrderEntities.forEach(cashbackOrderRepository::save);
  }

  private void saveCashbackPolicies(final PayCashback payCashback, final Cashback cashback, final List<CashbackPolicy> cashbackPolicies) {
    final var cashbackPolicyEntities = mapToPolicies(payCashback, cashback, cashbackPolicies);
    cashbackPolicyEntities.forEach(cashbackOrderPolicyRepository::save);
  }

  List<CashbackOrderPolicyEntity> mapToPolicies(final PayCashback payCashback, final Cashback cashback, final List<CashbackPolicy> cashbackPolicies) {
    return cashbackPolicies.stream()
        .map(policy -> {
          //cashbackPolicy type(@Precondition)에 맞는 매퍼를 가져 옵니다
          final var policyEntityMapper = cashbackPolicyEntityMapperConditioner.get(policy);
          return policyEntityMapper.map(payCashback, cashback, policy);
        })
        .collect(Collectors.toUnmodifiableList());
  }

  private void saveCashbackDetail(final PayCashback payCashback, final Cashback cashback) {
    final var cashbackDetailEntity = cashbackOrderDetailEntityMapper.map(payCashback, cashback);
    cashbackOrderDetailRepository.save(cashbackDetailEntity);
  }

  private void saveCashbackMember(final PayCashback payCashback) {
    final var cashbackOrderMemberEntity = cashbackOrderMemberEntityMapper.map(payCashback);
    cashbackOrderMemberRepository.save(cashbackOrderMemberEntity);
  }

  private void saveSmileCardCashback(final PayCashback payCashback, final SmileCardCashback smileCardCashback) {
    final var smilecardCashbackOrderEntity = smilecardCashbackOrderEntityMapper.map(payCashback, smileCardCashback);
    smilecardCashbackOrderRepository.save(smilecardCashbackOrderEntity);
  }

  private void saveSmileCardT2T3Cashback(final PayCashback payCashback, final SmileCardCashback smileCardCashback) {
    final var smilecardT2T3CashbackEntities = mapToT2T3Cashback(payCashback, smileCardCashback);
    smilecardT2T3CashbackEntities.forEach(smilecardT2T3CashbackRepository::save);
  }

  private List<SmilecardT2T3CashbackEntity> mapToT2T3Cashback(final PayCashback payCashback, final SmileCardCashback smileCardCashback) {
    return smileCardCashback.getT2t3Cashbacks()
        .stream()
        .filter(T2T3SmileCardCashback::isApply)
        .map(t3SmileCardCashback -> smilecardT2T3CashbackEntityMapper.map(payCashback, t3SmileCardCashback))
        .collect(Collectors.toUnmodifiableList());
  }
}
