package com.ebaykorea.payback.infrastructure.query.mapper;

import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderEntity;
import com.ebaykorea.payback.infrastructure.query.data.CashbackOrderQueryData;
import com.ebaykorea.payback.util.PaybackInstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {PaybackInstants.class}
)
public interface CashbackOrderQueryDataMapper {

  @Mapping(expression = "java(PaybackInstants.from(source.getUseEnableDt()))", target = "useEnableDate")
  CashbackOrderQueryData map(CashbackOrderEntity source);
}
