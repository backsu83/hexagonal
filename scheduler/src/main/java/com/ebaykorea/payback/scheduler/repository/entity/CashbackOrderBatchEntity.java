package com.ebaykorea.payback.scheduler.repository.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashbackOrderBatchEntity {

  public static final String FIND_BY_KEYS = "stardb.dbo.UPGMKT_Payback_CashbackOrderBatch_Select";
  public static final String UPDATE_STATUS = "stardb.dbo.UPGMKT_Payback_CashbackOrderBatch_Update";

  @Column(name = "ORDER_KEY")
  private String orderKey;

  @Column(name = "TX_KEY")
  private String txKey;

  @Column(name = "RSP_CD")
  private Long responseCode;

  @Column(name = "RSP_MSG_CD")
  private String messageCode;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "TRY_CNT")
  private Long retryCount;

  @Column(name = "INS_OPRT")
  private String insOprt;

  @Column(name = "INS_DATE")
  private Timestamp insDate;

  @Column(name = "UPD_OPRT")
  private String updOprt;

  @Column(name = "UPD_DATE")
  private Timestamp updDate;
}
