package com.ebaykorea.payback.scheduler.service.mapper;


import com.ebaykorea.payback.scheduler.service.entity.PaybackBatchRecord;
import com.ebaykorea.payback.scheduler.repository.entity.CashbackOrderBatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PaybackBatchRecordMapper {

  PaybackBatchRecord map(CashbackOrderBatchEntity entity);

}
