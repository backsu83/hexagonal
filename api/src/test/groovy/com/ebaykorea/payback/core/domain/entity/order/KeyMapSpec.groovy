package com.ebaykorea.payback.core.domain.entity.order

import spock.lang.Specification

import static com.ebaykorea.payback.grocery.OrderGrocery.KeyMap_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderUnitKey_생성

class KeyMapSpec extends Specification {
  def "KeyMap 검증"() {
    expect:
    def keyMap = 키맵정보
    keyMap.orderUnitKeyMap() == orderUnitKeyMap

    where:
    desc | 키맵정보                                                                                          | orderUnitKeyMap
    "기본" | KeyMap_생성()                                                                                   | ["orderUnitKey1": OrderUnitKey_생성()]
    "2개 orderUnits" | KeyMap_생성(orderUnitKeys: [OrderUnitKey_생성(), OrderUnitKey_생성(orderUnitKey: "orderUnitKey2")]) | ["orderUnitKey1": OrderUnitKey_생성(), "orderUnitKey2": OrderUnitKey_생성(orderUnitKey: "orderUnitKey2")]
  }
}
