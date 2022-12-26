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
public class CashbackOrderPolicyEntity {

  public static final String FIND_BY_ID = "stardb.dbo.UPGMKT_Payback_CashbackOrderPolicy_Select";
  public static final String SAVE = "stardb.dbo.UPGMKT_Payback_CashbackOrderPolicy_Insert";

  @Id
  @Column(name = "BUY_ORDER_NO")
  private Long orderNo;

  @Id
  @Column(name = "CASHBACK_TYPE")
  private String type;

  @Id
  @Column(name = "CASHBACK_POLICY_NO")
  private Long policyNo;

  @Column(name = "CASHBACK_POLICY_NM")
  private String name;

  @Column(name = "CASHBACK_SUB_TYPE")
  private String subType;

  @Column(name = "CASHBACK_SAVE_RATE")
  private BigDecimal saveRate;

  @Column(name = "CASHBACK_PAY_TYPE")
  private String payType;

  @Column(name = "CASHBACK_MAX_LIMIT_MONEY")
  private BigDecimal maxLimitMoney;

  @Column(name = "REG_ID")
  private String regId;

  @Column(name = "REG_DT")
  private Timestamp regDt;

  @Column(name = "CHARGE_PAY_SAVE_RATE")
  public BigDecimal chargePaySaveRate;

  @Column(name = "CHARGE_PAY_CLUB_SAVE_RATE")
  public BigDecimal chargePayClubSaveRate;

  @Column(name = "CHARGE_PAY_MAX_MONEY")
  public BigDecimal chargePayMaxMoney;

  @Column(name = "CHARGE_PAY_CLUB_MAX_MONEY")
  public BigDecimal chargePayClubMaxMoney;

  @Column(name = "CLUB_DAY_MAX_SAVE_RATE")
  public BigDecimal clubDayMaxSaveRate;

  @Column(name = "CLUB_DAY_MAX_SAVE_MONEY")
  public BigDecimal clubDayMaxSaveMoney;
}
