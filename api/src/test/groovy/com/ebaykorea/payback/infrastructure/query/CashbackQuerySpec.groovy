package com.ebaykorea.payback.infrastructure.query

import com.ebaykorea.payback.infrastructure.gateway.TransactionGatewayImpl
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderRepository
import com.ebaykorea.payback.infrastructure.query.mapper.CashbackOrderQueryDataMapper
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.OrderGrocery.KeyMap_생성
import static java.util.Collections.emptyList

class CashbackQuerySpec extends Specification {
  def transactionGateway = Mock(TransactionGatewayImpl)
  def cashbackOrderRepository = Mock(CashbackOrderRepository)
  def cashbackOrderQueryDataMapper = Mappers.getMapper(CashbackOrderQueryDataMapper)

  def cashbackQuery = new CashbackQuery(transactionGateway, cashbackOrderRepository, cashbackOrderQueryDataMapper)

  def "파라미터에 따라 올바른 조건으로 조회로직을 호출하는지 확인"() {
    when:
    cashbackQuery.getSavedCashback(packNo, txKey, orderKey)

    then:
    gatewayCount * transactionGateway.getKeyMap(_, _) >> KeyMap_생성()
    repositoryCount * cashbackOrderRepository.findByPackNo(_) >> emptyList()

    where:
    desc                     | packNo | txKey   | orderKey   | gatewayCount | repositoryCount
    "packNo가 입력된 경우"         | 1L     | null    | null       | 0            | 1
    "txKey,orderKey가 입력된 경우" | null   | "txKey" | "orderKey" | 1            | 1
    "txKey만 입력된 경우"          | null   | "txKey" | null       | 0            | 0
    "값이 없는 경우"               | null   | null    | null       | 0            | 0
  }
}
