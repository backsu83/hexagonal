package com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
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
public class CashbackOrderDetailEntity {

  public static final String FIND_BY_ID = "stardb.dbo.UPGMKT_Payback_CashbackOrderDetail_Select";
  public static final String SAVE = "stardb.dbo.UPGMKT_Payback_CashbackOrderDetail_Insert";

  @Id
  @Column(name = "ORDER_NO")
  private Long orderNo;

  @Column(name = "ITEM_AMOUNT")
  private BigDecimal itemAmount;

  @Column(name = "SELLER_AMOUNT")
  private BigDecimal sellerAmount;

  @Column(name = "PAY_AMOUNT")
  private BigDecimal payAmount;

  @Column(name = "CLUB_AMOUNT")
  private BigDecimal clubAmount;

  @Column(name = "ITEM_CASHBACK_APPLY_YN")
  private String itemCashbackApplyYn;

  @Column(name = "PAY_CASHBACK_APPLY_YN")
  private String payCashbackApplyYn;

  @Column(name = "CLUB_CASHBACK_APPLY_YN")
  private String clubCashbackApplyYn;

  @Column(name = "REG_ID")
  private String regId;

  @Column(name = "REG_DT")
  private Timestamp regDt;

  @Column(name = "CHG_ID")
  private String chgId;

  @Column(name = "CHG_DT")
  private Timestamp chgDt;

  @Column(name = "CHARGE_PAY_REWARD")
  public BigDecimal chargePayReward;

  @Column(name = "CHARGE_PAY_REWARD_CLUB")
  public BigDecimal chargePayRewardClub;

  @Column(name = "CLUB_DAY_AMOUNT")
  public BigDecimal clubDayAmount;

  @Column(name = "CLUB_DAY_APPLY_YN")
  public String clubDayApplyYn;

}
