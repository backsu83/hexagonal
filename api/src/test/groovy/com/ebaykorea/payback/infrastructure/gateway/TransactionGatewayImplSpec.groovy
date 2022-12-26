package com.ebaykorea.payback.infrastructure.gateway

import com.ebaykorea.payback.infrastructure.gateway.client.transaction.TransactionApiClient
import com.ebaykorea.payback.infrastructure.gateway.mapper.TransactionGatewayMapper
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.OrderGrocery.KeyMap_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderUnitKey_생성
import static com.ebaykorea.payback.grocery.TransactionApiGrocery.KeyMapDto_생성
import static com.ebaykorea.payback.grocery.TransactionApiGrocery.KeyMapResponseDto_생성

class TransactionGatewayImplSpec extends Specification {
  def client = Stub(TransactionApiClient)
  def mapper = Mappers.getMapper(TransactionGatewayMapper)
  def transactionGateway = new TransactionGatewayImpl(client, mapper)

  def "transactionGateway 결과가 정상인지 확인한다"() {
    setup:
    client.findKeyMaps(_ as String, _ as String, _ as String) >> Optional.of(response)

    expect:
    def result = transactionGateway.getKeyMap("txKey", "orderKey")
    result == expectResult

    where:
    desc    | response                                                                                                              | expectResult
    "기본"    | KeyMapResponseDto_생성()                                                                                              | KeyMap_생성()
    "여러 주문" | KeyMapResponseDto_생성(orders: [KeyMapDto_생성(), KeyMapDto_생성(orderUnitKey: "orderUnitKey2", orderNo: 2L, contrNo: 2L)]) | KeyMap_생성(orderUnitKeys: [OrderUnitKey_생성(), OrderUnitKey_생성(orderUnitKey: "orderUnitKey2", buyOrderNo: 2L, contrNo: 2L)])
  }
}
