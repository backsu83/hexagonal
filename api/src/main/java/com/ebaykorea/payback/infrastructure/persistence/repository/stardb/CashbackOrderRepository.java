package com.ebaykorea.payback.infrastructure.persistence.repository.stardb;

import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderEntity;
import com.ebaykorea.saturn.mssql.dbname.Gmkt;
import com.ebaykorea.saturn.starter.annotation.SaturnDataSource;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedure;
import com.ebaykorea.saturn.starter.annotation.SaturnProcedureParameter;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Repository
@SaturnDataSource(name = Gmkt.TIGER_READ)
public class CashbackOrderRepository {

  @SaturnProcedure(
      procedureName = CashbackOrderEntity.FIND_BY_ID,
      parameters = {
          @SaturnProcedureParameter(name = "BUY_ORDER_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "CASHBACK_TYPE", sqlType = Types.CHAR, scale = 1),
          @SaturnProcedureParameter(name = "TRADE_CD", sqlType = Types.VARCHAR, scale = 3)
      }
  )
  public Optional<CashbackOrderEntity> findById(final CashbackOrderEntity id) {
    return Optional.empty();
  }

  @SaturnProcedure(
      procedureName = CashbackOrderEntity.FIND_BY_PACKNO,
      parameters = {
          @SaturnProcedureParameter(name = "PACK_NO", sqlType = Types.BIGINT)
      }
  )
  public List<CashbackOrderEntity> findByPackNo(final long packNo) {
    return Collections.emptyList();
  }

  @SaturnProcedure(
      procedureName = CashbackOrderEntity.SAVE,
      parameters = {
          @SaturnProcedureParameter(name = "BUY_ORDER_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "CASHBACK_TYPE", sqlType = Types.CHAR, scale = 1),
          @SaturnProcedureParameter(name = "TRADE_CD", sqlType = Types.VARCHAR, scale = 3),
          @SaturnProcedureParameter(name = "CASHBACK_MONEY", sqlType = Types.DECIMAL),
          @SaturnProcedureParameter(name = "CASHBACK_BASIS_MONEY", sqlType = Types.DECIMAL),

          @SaturnProcedureParameter(name = "GD_NO", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "PACK_NO", sqlType = Types.BIGINT),
          @SaturnProcedureParameter(name = "CUST_NO", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "USER_KEY", sqlType = Types.VARCHAR, scale = 200),
          @SaturnProcedureParameter(name = "TRADE_STATUS", sqlType = Types.CHAR, scale = 2),

          @SaturnProcedureParameter(name = "USE_ENABLE_DT", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "SITE_TYPE", sqlType = Types.CHAR, scale = 1),
          @SaturnProcedureParameter(name = "REG_ID", sqlType = Types.VARCHAR, scale = 10),
          @SaturnProcedureParameter(name = "REG_DT", sqlType = Types.TIMESTAMP),
          @SaturnProcedureParameter(name = "SMILE_CLUB_YN", sqlType = Types.CHAR, scale = 1),

          @SaturnProcedureParameter(name = "SHOP_TYPE", sqlType = Types.CHAR, scale = 2)
      },
      throwEx = true //입력 실패시 exception 발생
  )
  public void save(final CashbackOrderEntity cashbackOrder) {
    //TODO: custom exception 발행 필요시 throwEx 옵션 제거 후 여기 작성
  }

}
