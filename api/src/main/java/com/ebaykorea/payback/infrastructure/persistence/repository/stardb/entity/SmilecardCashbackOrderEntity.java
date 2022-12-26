package com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmilecardCashbackOrderEntity {
  public static final String FIND_BY_ID = "stardb.dbo.UPGMKT_Payback_SmilecardCashbackOrder_Select";
  public static final String SAVE = "stardb.dbo.UPGMKT_Payback_SmilecardCashbackOrder_Insert";

  @Id
  @Column(name = "PACK_NO")
  private long packNo;

  @Column(name = "SMILECARD_CASHBACK_AMOUNT")
  private BigDecimal cashbackAmount;

  @Column(name = "SMILECARD_CASHBACK_APPLY_YN")
  private String applyYn;

  @Column(name = "REG_ID")
  private String regId;

  @Column(name = "REG_DT")
  private Timestamp regDt;

  @Column(name = "CHG_ID")
  private String chgId;

  @Column(name = "CHG_DT")
  private Timestamp chgDt;

  @Column(name = "T2T3_CASHBACK_AMOUNT")
  private BigDecimal t2t3CashbackAmount;

  @Column(name = "T2T3_CASHBACK_APPLY_YN")
  private String t2t3ApplyYn;

  @Column(name = "ITEM_TYPE")
  private String itemType;
}
