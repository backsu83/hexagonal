package com.ebaykorea.payback.infrastructure.persistence.repository.stardb;

import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.SmilecardT2T3CashbackEntity;
import com.ebaykorea.saturn.mssql.dbname.Gmkt;
import com.ebaykorea.saturn.starter.annotation.SaturnDataSource;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedure;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedureParameter;
import java.sql.Types;
import org.springframework.stereotype.Repository;


@Repository
@SaturnDataSource(name = Gmkt.TIGER_READ)
public class SmilecardT2T3CashbackRepository {

  @SaturnProcedure(
      procedureName = SmilecardT2T3CashbackEntity.SAVE,
      parameters = {
          @SaturnProcedureParameter(name = "PACK_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "BUY_ORDER_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "ITEM_PRICE", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "T2T3_CASHBACK_AMNT", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "SMILE_CARD_TYPE", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "SITE_CODE", sqlType = Types.VARCHAR, scale = 20),
          @SaturnProcedureParameter(name = "INS_DATE", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "INS_OPRT", sqlType = Types.VARCHAR, scale = 50),
          @SaturnProcedureParameter(name = "UPD_DATE", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "UPD_OPRT", sqlType = Types.VARCHAR, scale = 50),
          @SaturnProcedureParameter(name = "ITEM_TYPE", sqlType = Types.VARCHAR, scale = 2)
      },
      throwEx = true //입력 실패시 exception 발생
  )
  public void save(final SmilecardT2T3CashbackEntity entity) {

  }
}
