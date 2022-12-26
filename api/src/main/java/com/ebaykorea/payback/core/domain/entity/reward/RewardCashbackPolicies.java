package com.ebaykorea.payback.core.domain.entity.reward;

import static com.ebaykorea.payback.util.PaybackCollections.toMapBy;
import static com.ebaykorea.payback.util.PaybackDecimals.summarizing;
import static com.ebaykorea.payback.util.PaybackInstants.DATE_TIME_FORMATTER;
import static com.ebaykorea.payback.util.PaybackInstants.getDefaultEnableDate;
import static com.ebaykorea.payback.util.PaybackStrings.isBlank;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import lombok.Value;

@Value
public class RewardCashbackPolicies {
  Map<Long, List<RewardCashbackPolicy>> cashbackPolicyMap;
  Map<Long, RewardBackendCashbackPolicy> backendCashbackPolicyMap;
  Map<Long, RewardT2T3SmileCardCashbackPolicy> smileCardCashbackPolicyMap;

  String useEnableDate;
  /**
   * 스마일카드(T0) 사용할 경우 적립예정금액
   */
  BigDecimal smileCardCashbackAmount;
  /**
   * 뉴스마일카드(T1,T2,T3) 사용할 경우 적립예정금액
   */
  BigDecimal newSmileCardCashbackAmount;

  public static RewardCashbackPolicies EMPTY = RewardCashbackPolicies.of(emptyList(), emptyList(), emptyList(), null, BigDecimal.ZERO, BigDecimal.ZERO);

  public static RewardCashbackPolicies of(
      final List<RewardCashbackPolicy> cashbackPolicies,
      final List<RewardBackendCashbackPolicy> backendCashbackPolicies,
      final List<RewardT2T3SmileCardCashbackPolicy> smileCardCashbackPolicies,
      final String useEnableDate,
      final BigDecimal smileCardCashbackAmount,
      final BigDecimal newSmileCardCashbackAmount) {
    return new RewardCashbackPolicies(
        cashbackPolicies.stream().collect(groupingBy(RewardCashbackPolicy::getPolicyKey)),
        backendCashbackPolicies.stream().collect(toMapBy(RewardBackendCashbackPolicy::getPolicyKey)),
        smileCardCashbackPolicies.stream().collect(toMapBy(RewardT2T3SmileCardCashbackPolicy::getPolicyKey)),
        useEnableDate,
        smileCardCashbackAmount,
        newSmileCardCashbackAmount);
  }

  private RewardCashbackPolicies(
      final Map<Long, List<RewardCashbackPolicy>> cashbackPolicyMap,
      final Map<Long, RewardBackendCashbackPolicy> backendCashbackPolicyMap,
      final Map<Long, RewardT2T3SmileCardCashbackPolicy> smileCardCashbackPolicyMap,
      final String useEnableDate,
      final BigDecimal smileCardCashbackAmount,
      final BigDecimal newSmileCardCashbackAmount) {
    this.cashbackPolicyMap = cashbackPolicyMap;
    this.backendCashbackPolicyMap = backendCashbackPolicyMap;
    this.smileCardCashbackPolicyMap = smileCardCashbackPolicyMap;
    this.useEnableDate = useEnableDate;
    this.smileCardCashbackAmount = smileCardCashbackAmount;
    this.newSmileCardCashbackAmount = newSmileCardCashbackAmount;

    validate();
  }

  public void validate() {

  }

  //정책이 여러개 등록될 경우를 고려하여 key 및 타입별 캐시백 금액을 Sum한 형태로 가져옵니다
  //TODO: https://jira.ebaykorea.com/browse/RWD-971 확인 필요
  public BigDecimal getCashbackAmount(final long policyKey, final CashbackType cashbackType) {
    return findRewardCashbackPolicies(policyKey).stream()
        .filter(p -> p.getCashbackCd() == cashbackType)
        .map(RewardCashbackPolicy::getCashbackAmount)
        .map(BigDecimal::valueOf)
        .collect(summarizing());
  }

  public Instant toUseEnableDate(final Instant orderDate) {
    if (isBlank(useEnableDate)) {
      return getDefaultEnableDate(orderDate);
    } else {
      return DATE_TIME_FORMATTER.parse(useEnableDate, Instant::from);
    }
  }

  public List<RewardCashbackPolicy> findRewardCashbackPolicies(final long policyKey) {
    return Optional.ofNullable(cashbackPolicyMap.get(policyKey))
        .orElse(emptyList());
  }

  public Optional<RewardBackendCashbackPolicy> findBackendRewardCashbackPolicy(final long policyKey) {
    return Optional.ofNullable(backendCashbackPolicyMap.get(policyKey));
  }
}
