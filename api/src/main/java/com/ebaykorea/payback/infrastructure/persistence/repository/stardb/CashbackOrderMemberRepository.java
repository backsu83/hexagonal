package com.ebaykorea.payback.infrastructure.persistence.repository.stardb;

import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderMemberEntity;
import com.ebaykorea.saturn.mssql.dbname.Gmkt;
import com.ebaykorea.saturn.starter.annotation.SaturnDataSource;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedure;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedureParameter;
import java.sql.Types;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
@SaturnDataSource(name = Gmkt.TIGER_READ)
public class CashbackOrderMemberRepository {

  @SaturnProcedure(
      procedureName = CashbackOrderMemberEntity.FIND_BY_ID,
      parameters = {
          @SaturnProcedureParameter(name = "PACK_NO", sqlType = Types.BIGINT)
      }
  )
  public Optional<CashbackOrderMemberEntity> findById(final Long packNo) {
    return Optional.empty();
  }

  @SaturnProcedure(
      procedureName = CashbackOrderMemberEntity.SAVE,
      parameters = {
          @SaturnProcedureParameter(name = "PACK_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "CUST_NO", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "REG_SITE", sqlType = Types.VARCHAR, scale = 4),
          @SaturnProcedureParameter(name = "PAY_TYPE", sqlType = Types.VARCHAR, scale = 4),
          @SaturnProcedureParameter(name = "MEM_GRADE", sqlType = Types.VARCHAR, scale = 4),
          @SaturnProcedureParameter(name = "CLUB_CHECK_YN", sqlType = Types.CHAR, scale = 1),
          @SaturnProcedureParameter(name = "INS_DATE", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "INS_OPRT", sqlType = Types.VARCHAR, scale = 50),
          @SaturnProcedureParameter(name = "UPD_DATE", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "UPD_OPRT", sqlType = Types.VARCHAR, scale = 50),
      },
      throwEx = true //입력 실패시 exception 발생
  )
  public void save(final CashbackOrderMemberEntity cashbackOrderMemberEntity) {
  }
}
