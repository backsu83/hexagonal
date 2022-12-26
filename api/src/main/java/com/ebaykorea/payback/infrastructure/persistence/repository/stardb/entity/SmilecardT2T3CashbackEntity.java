package com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmilecardT2T3CashbackEntity {
  public static final String SAVE = "stardb.dbo.UP_GMKT_FRONT_INSERT_SMILECARD_T2T3_CASHBACK";

  @Id
  @Column(name = "PACK_NO")
  private Long packNo;

  @Id
  @Column(name = "BUY_ORDER_NO")
  private Long orderNo;

  @Column(name = "ITEM_PRICE")
  private BigDecimal itemPrice;

  @Column(name = "T2T3_CASHBACK_AMNT")
  private BigDecimal t2t3CashbackAmount;

  @Column(name = "SMILE_CARD_TYPE")
  private String smileCardType;

  @Column(name = "SITE_CODE")
  private String siteCode;

  @Column(name = "INS_OPRT")
  private String insOprt;

  @Column(name = "INS_DATE")
  private Timestamp insDate;

  @Column(name = "UPD_OPRT")
  private String updOprt;

  @Column(name = "UPD_DATE")
  private Timestamp updDate;

  @Column(name = "ITEM_TYPE")
  private String itemType;
}
