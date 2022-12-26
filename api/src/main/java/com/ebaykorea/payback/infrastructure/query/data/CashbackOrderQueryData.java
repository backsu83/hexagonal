package com.ebaykorea.payback.infrastructure.query.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashbackOrderQueryData {
  private BigDecimal amount;
  private String cashbackType;
  private String itemNo;
  private String buyerNo;
  private String tradeStatus;
  private Instant useEnableDate;
  private String smileClubYn;
  private String shopType;
}
