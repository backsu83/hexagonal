package com.ebaykorea.payback.core.domain.entity.cashback;

import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.SmileCardCashback;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Getter
@EqualsAndHashCode
@ToString
public class PayCashback {

  private final long packNo;
  private final Instant orderDate;
  private final Member member;

  private final List<Cashback> cashbacks;
  private final SmileCardCashback smileCardCashback;

  public static PayCashback of(
      final long packNo,
      final Instant orderDate,
      final Member member,
      final List<Cashback> cashbacks,
      final SmileCardCashback smileCardCashback
  ) {
    return new PayCashback(packNo, orderDate, member, cashbacks, smileCardCashback);
  }

  private PayCashback(
      final long packNo,
      final Instant orderDate,
      final Member member,
      final List<Cashback> cashbacks,
      final SmileCardCashback smileCardCashback
  ) {
    this.packNo = packNo;
    this.orderDate = orderDate;
    this.member = member;
    this.cashbacks = cashbacks;
    this.smileCardCashback = smileCardCashback;

    validate();
  }

  //불변식
  private void validate() {

  }

  public boolean hasSmileCardCashback() {
    return Optional.ofNullable(smileCardCashback)
        .map(s -> s.isApply() || s.isApplyT2T3())
        .orElse(false);
  }


}
