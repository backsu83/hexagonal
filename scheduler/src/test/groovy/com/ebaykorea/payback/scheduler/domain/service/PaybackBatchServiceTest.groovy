package com.ebaykorea.payback.scheduler.domain.service

import com.ebaykorea.payback.scheduler.repository.CashbackOrderBatchRepository
import com.ebaykorea.payback.scheduler.service.entity.ProcessType
import com.ebaykorea.payback.scheduler.client.PaybackApiClient
import com.ebaykorea.payback.scheduler.client.dto.PaybackRequestDto
import com.ebaykorea.payback.scheduler.client.dto.PaybackResponseDto
import com.ebaykorea.payback.scheduler.repository.entity.CashbackOrderBatchEntity

import com.ebaykorea.payback.scheduler.service.PaybackBatchService
import com.ebaykorea.payback.scheduler.service.mapper.PaybackBatchRecordMapper
import org.mapstruct.factory.Mappers
import spock.lang.Specification
import java.util.concurrent.Executors
import java.sql.Timestamp
import java.time.Instant

class PaybackBatchServiceTest extends Specification {

  def cashbackOrderBatchRepository = Mock(CashbackOrderBatchRepository)
  def paybackApiClient = Stub(PaybackApiClient)
  def paybackBatchRecordMapper = Mappers.getMapper(PaybackBatchRecordMapper)
  def paybackBatchService = new PaybackBatchService(
          cashbackOrderBatchRepository,
          paybackBatchRecordMapper,
          paybackApiClient,
          Executors.newFixedThreadPool(2)
  )

  def "UpdateRecords"() {
    given:
    def response = PaybackResponseDto.builder()
            .code("200")
            .data(PaybackResponseDto.Body.builder()
                    .orderKey("orderKey1")
                    .txKey("teKey1")
                    .build())
            .message("CREATED")
            .build()

    def records = [
            CashbackOrderBatchEntity.builder()
                    .orderKey("orderKey1")
                    .txKey("teKey1")
                    .responseCode(500L)
                    .retryCount(0L)
                    .status("TARGET")
                    .messageCode(null)
                    .insDate(Timestamp.from(Instant.now()))
                    .insOprt("test")
                    .updDate(Timestamp.from(Instant.now()))
                    .updOprt("test")
                    .build(),
            CashbackOrderBatchEntity.builder()
                     .orderKey("orderKey2")
                     .txKey("teKey2")
                     .responseCode(500L)
                     .retryCount(0L)
                     .status("TARGET")
                     .messageCode(null)
                     .insDate(Timestamp.from(Instant.now()))
                     .insOprt("test")
                     .updDate(Timestamp.from(Instant.now()))
                     .updOprt("test")
                     .build()
    ]
    cashbackOrderBatchRepository.findNoCompleted() >> records
    paybackApiClient.saveCashbacks(_ as PaybackRequestDto) >>> response >> {throw new Exception()}

    when:
    paybackBatchService.updateRecords()

    then:
    1 * cashbackOrderBatchRepository.updateStatus(_ ,_ , ProcessType.COMPLETED.name() , 0L , _) >> {}
    1 * cashbackOrderBatchRepository.updateStatus(_ ,_ , ProcessType.FAIL.name() , !0L , _) >> {}
  }

}
