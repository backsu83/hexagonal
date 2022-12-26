package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.infrastructure.gateway.client.transaction.dto.KeyMapDto
import com.ebaykorea.payback.infrastructure.gateway.client.transaction.dto.KeyMapResponseDto

class TransactionApiGrocery {
  static def KeyMapResponseDto_생성(Map map = [:]) {
    new KeyMapResponseDto().tap {
      orders = (map.orders ?: [KeyMapDto_생성()]) as List<KeyMapDto>
    }
  }

  static def KeyMapDto_생성(Map map = [:]) {
    new KeyMapDto().tap {
      txKey = (map.txKey ?: "txKey") as String
      orderKey = (map.orderKey ?: "orderKey") as String
      packNo = (map.packNo ?: 1L) as Long
      orderUnitKey = (map.orderUnitKey ?: "orderUnitKey1") as String
      orderNo = (map.orderNo ?: 1L) as Long
      contrNo = (map.contrNo ?: 1L) as Long
    }
  }
}
