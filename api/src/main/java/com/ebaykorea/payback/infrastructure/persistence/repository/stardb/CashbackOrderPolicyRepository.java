package com.ebaykorea.payback.infrastructure.persistence.repository.stardb;

import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderPolicyEntity;
import com.ebaykorea.saturn.mssql.dbname.Gmkt;
import com.ebaykorea.saturn.starter.annotation.SaturnDataSource;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedure;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedureParameter;
import java.sql.Types;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
@SaturnDataSource(name = Gmkt.TIGER_READ)
public class CashbackOrderPolicyRepository {

  @SaturnProcedure(
      procedureName = CashbackOrderPolicyEntity.FIND_BY_ID,
      parameters = {
          @SaturnProcedureParameter(name = "BUY_ORDER_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "CASHBACK_TYPE", sqlType = Types.CHAR),
          @SaturnProcedureParameter(name = "CASHBACK_POLICY_NO", sqlType = Types.BIGINT)
      }
  )
  public Optional<CashbackOrderPolicyEntity> findById(final CashbackOrderPolicyEntity id) {
    return Optional.empty();
  }

  @SaturnProcedure(
      procedureName = CashbackOrderPolicyEntity.SAVE,
      parameters = {
          @SaturnProcedureParameter(name = "BUY_ORDER_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "CASHBACK_TYPE", sqlType = Types.CHAR),
          @SaturnProcedureParameter(name = "CASHBACK_POLICY_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "CASHBACK_POLICY_NM", sqlType = Types.VARCHAR, scale = 200),
          @SaturnProcedureParameter(name = "CASHBACK_SUB_TYPE", sqlType = Types.CHAR),
          @SaturnProcedureParameter(name = "CASHBACK_SAVE_RATE", sqlType = Types.NUMERIC),
          @SaturnProcedureParameter(name = "CASHBACK_PAY_TYPE", sqlType = Types.CHAR),
          @SaturnProcedureParameter(name = "CASHBACK_MAX_LIMIT_MONEY", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "REG_ID", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "REG_DT", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "CHARGE_PAY_SAVE_RATE", sqlType = Types.NUMERIC),
          @SaturnProcedureParameter(name = "CHARGE_PAY_CLUB_SAVE_RATE", sqlType = Types.NUMERIC),
          @SaturnProcedureParameter(name = "CHARGE_PAY_MAX_MONEY", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "CHARGE_PAY_CLUB_MAX_MONEY", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "CLUB_DAY_MAX_SAVE_RATE", sqlType = Types.NUMERIC),
          @SaturnProcedureParameter(name = "CLUB_DAY_MAX_SAVE_MONEY", sqlType = Types.DECIMAL),
      },
      throwEx = true //입력 실패시 exception 발생
  )
  public void save(final CashbackOrderPolicyEntity cashbackOrderPolicyEntity) {
  }

}
