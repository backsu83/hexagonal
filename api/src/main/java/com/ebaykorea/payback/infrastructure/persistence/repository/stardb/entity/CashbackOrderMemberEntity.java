package com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity;

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
public class CashbackOrderMemberEntity {

  public static final String FIND_BY_ID = "stardb.dbo.UPGMKT_Payback_CashbackOrderMember_Select";
  public static final String SAVE = "stardb.dbo.UPGMKT_Payback_CashbackOrderMember_Insert";

  @Id
  @Column(name = "PACK_NO")
  private Long packNo;

  @Column(name = "CUST_NO")
  private String buyerNo;

  @Column(name = "REG_SITE")
  private String regSite;

  @Column(name = "PAY_TYPE")
  private String payType;

  @Column(name = "MEM_GRADE")
  private String memberGrade;

  @Column(name = "CLUB_CHECK_YN")
  private String clubCheckYn;

  @Column(name = "INS_OPRT")
  private String insOprt;

  @Column(name = "INS_DATE")
  private Timestamp insDate;

  @Column(name = "UPD_OPRT")
  private String updOprt;

  @Column(name = "UPD_DATE")
  private Timestamp updDate;
}
