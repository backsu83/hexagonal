package com.ebaykorea.payback.core;


import static com.ebaykorea.payback.core.domain.constant.ResponseMessageType.CASHBACK_CREATED;
import static com.ebaykorea.payback.core.domain.constant.ResponseMessageType.CASHBACK_DUPLICATED;
import static com.ebaykorea.payback.core.domain.constant.ResponseMessageType.CASHBACK_INVALID_TARGET;

import com.ebaykorea.payback.core.domain.constant.ResponseMessageType;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicies;
import com.ebaykorea.payback.core.factory.PayCashbackCreator;
import com.ebaykorea.payback.core.gateway.OrderGateway;
import com.ebaykorea.payback.core.gateway.PaymentGateway;
import com.ebaykorea.payback.core.gateway.RewardGateway;
import com.ebaykorea.payback.core.gateway.TransactionGateway;
import com.ebaykorea.payback.core.repository.PayCashbackRepository;
import com.ebaykorea.payback.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashbackApplicationService {
  private final OrderGateway orderGateway;
  private final PaymentGateway paymentGateway;
  private final TransactionGateway transactionGateway;
  private final RewardGateway rewardGateway;

  private final MemberService memberService;
  private final PayCashbackCreator payCashbackCreator;

  private final PayCashbackRepository payCashbackRepository;

  public ResponseMessageType setCashback(final String txKey, final String orderKey) {
    //주문 정보
    final var order = orderGateway.getOrder(orderKey);

    if (!order.isForCashback()) {
      return CASHBACK_INVALID_TARGET;
    }

    //주문 키 매핑 정보
    final var orderKeyMap = transactionGateway.getKeyMap(txKey, orderKey);

    //캐시백 중복 체크
    if (payCashbackRepository.isDuplicatedCashback(orderKeyMap)) {
      return CASHBACK_DUPLICATED;
    }

    //결제 정보
    final var paymentRecordFuture = paymentGateway.getPaymentRecordAsync(order.getPaySeq());
    //상품 스냅샷 정보
    final var itemSnapshotsFuture = orderGateway.getItemSnapshotAsync(order.findItemSnapshotKeys());
    //회원 정보
    final var memberFuture = memberService.getMemberAsync(order.getBuyer());

    final var paymentRecord = paymentRecordFuture.join();
    final var itemSnapshots = itemSnapshotsFuture.join();

    //리워드 캐시백 정책 조회
    final var rewardCashbackPolicies = paymentRecord.hasMainPaymentAmount() ? //주 결제수단 금액이 있는 경우에만 정책 조회
        rewardGateway.getCashbackPolicies(order, paymentRecord, itemSnapshots.getItemSnapshotMap(), orderKeyMap.orderUnitKeyMap()) :
        RewardCashbackPolicies.EMPTY;

    final var member = memberFuture.join();

    final var payCashback = payCashbackCreator.create(orderKeyMap, order, member, paymentRecord, itemSnapshots, rewardCashbackPolicies);

    //payCashback 저장
    payCashbackRepository.save(payCashback);

    return CASHBACK_CREATED;
  }
}
