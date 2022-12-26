package com.ebaykorea.payback.infrastructure.persistence.repository.stardb;

import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderDetailEntity;
import com.ebaykorea.saturn.mssql.dbname.Gmkt;
import com.ebaykorea.saturn.starter.annotation.SaturnDataSource;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedure;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedureParameter;

import java.sql.Types;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
@SaturnDataSource(name = Gmkt.TIGER_READ)
public class CashbackOrderDetailRepository {

  @SaturnProcedure(
      procedureName = CashbackOrderDetailEntity.FIND_BY_ID,
      parameters = {
          @SaturnProcedureParameter(name = "ORDER_NO", sqlType = Types.BIGINT)
      }
  )
  public Optional<CashbackOrderDetailEntity> findById(final Long orderNo) {
    return Optional.empty();
  }

  @SaturnProcedure(
      procedureName = CashbackOrderDetailEntity.SAVE,
      parameters = {
          @SaturnProcedureParameter(name = "ORDER_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "ITEM_AMOUNT", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "SELLER_AMOUNT", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "PAY_AMOUNT", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "CLUB_AMOUNT", sqlType = Types.DECIMAL),

          @SaturnProcedureParameter(name = "ITEM_CASHBACK_APPLY_YN", sqlType = Types.VARCHAR, scale = 1),
          @SaturnProcedureParameter(name = "PAY_CASHBACK_APPLY_YN", sqlType = Types.VARCHAR, scale = 1),
          @SaturnProcedureParameter(name = "CLUB_CASHBACK_APPLY_YN", sqlType = Types.VARCHAR, scale = 1),

          @SaturnProcedureParameter(name = "REG_ID", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "REG_DT", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "CHG_ID", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "CHG_DT", sqlType = Types.TIMESTAMP),

          @SaturnProcedureParameter(name = "CHARGE_PAY_REWARD", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "CHARGE_PAY_REWARD_CLUB", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "CLUB_DAY_AMOUNT", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "CLUB_DAY_APPLY_YN", sqlType = Types.CHAR, scale = 1)
      },
      throwEx = true //입력 실패시 exception 발생
  )
  public void save( CashbackOrderDetailEntity cashbackOrderDetailEntity) {
  }
}
