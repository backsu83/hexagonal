package com.ebaykorea.payback.infrastructure.query.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static com.ebaykorea.payback.util.PaybackDecimals.summarizing;
import static java.util.Collections.emptyList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedCashbackQueryResult {
  private BigDecimal totalCashbackAmount;
  private List<CashbackOrderQueryData> cashbackOrders;

  public static SavedCashbackQueryResult EMPTY = SavedCashbackQueryResult.of(emptyList());

  public static SavedCashbackQueryResult of(final List<CashbackOrderQueryData> cashbackOrders) {
    return new SavedCashbackQueryResult(cashbackOrders);
  }

  private SavedCashbackQueryResult(final List<CashbackOrderQueryData> cashbackOrders) {
    this.totalCashbackAmount = cashbackOrders.stream().map(CashbackOrderQueryData::getAmount).collect(summarizing());
    this.cashbackOrders = cashbackOrders;
  }
}
