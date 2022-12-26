package com.ebaykorea.payback.infrastructure.persistence.repository

import com.ebaykorea.payback.infrastructure.persistence.mapper.CashbackOrderDetailEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.mapper.CashbackOrderEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.mapper.CashbackOrderMemberEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.mapper.ChargePayPolicyEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.mapper.ClubDayPolicyEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.mapper.DefaultCashbackPolicyEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.mapper.SmilecardCashbackOrderEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.mapper.SmilecardT2T3CashbackEntityMapper
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderDetailRepository
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderMemberRepository
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderPolicyRepository
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.CashbackOrderRepository
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.SmilecardCashbackOrderRepository
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.SmilecardT2T3CashbackRepository
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderDetailEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderMemberEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderPolicyEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.SmilecardCashbackOrderEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.SmilecardT2T3CashbackEntity
import com.ebaykorea.payback.util.support.Conditioner
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.CashbackOrderPolicyEntity_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ChargePayCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ClubDayCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ItemCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SellerCashback_생성
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SmilePayCashback_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.Cashback_생성
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.PayCashback_생성
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.SmileCardCashback_생성
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.T2T3SmileCardCashback_생성
import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.CashbackOrderDetailEntity_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.KeyMap_생성

class PayCashbackRepositoryImplSpec extends Specification {
  def cashbackOrderRepository = Mock(CashbackOrderRepository)
  def cashbackOrderPolicyRepository = Mock(CashbackOrderPolicyRepository)
  def cashbackOrderDetailRepository = Mock(CashbackOrderDetailRepository)
  def cashbackOrderMemberRepository = Mock(CashbackOrderMemberRepository)
  def smilecardCashbackOrderRepository = Mock(SmilecardCashbackOrderRepository)
  def smilecardT2T3CashbackRepository = Mock(SmilecardT2T3CashbackRepository)

  def cashbackOrderEntityMapper = Mappers.getMapper(CashbackOrderEntityMapper)
  def chargePayPolicyEntityMapper = Mappers.getMapper(ChargePayPolicyEntityMapper)
  def clubDayPolicyEntityMapper = Mappers.getMapper(ClubDayPolicyEntityMapper)
  def defaultCashbackPolicyEntityMapper = Mappers.getMapper(DefaultCashbackPolicyEntityMapper)
  def cashbackOrderDetailEntityMapper = Mappers.getMapper(CashbackOrderDetailEntityMapper)
  def cashbackOrderMemberEntityMapper = Mappers.getMapper(CashbackOrderMemberEntityMapper)
  def smilecardCashbackOrderEntityMapper = Mappers.getMapper(SmilecardCashbackOrderEntityMapper)
  def smilecardT2T3CashbackEntityMapper = Mappers.getMapper(SmilecardT2T3CashbackEntityMapper)

  def conditioner = Conditioner.of([
      chargePayPolicyEntityMapper,
      clubDayPolicyEntityMapper,
      defaultCashbackPolicyEntityMapper
  ])

  def repository = new PayCashbackRepositoryImpl(
      cashbackOrderRepository,
      cashbackOrderPolicyRepository,
      cashbackOrderDetailRepository,
      cashbackOrderMemberRepository,
      smilecardCashbackOrderRepository,
      smilecardT2T3CashbackRepository,
      cashbackOrderEntityMapper,
      conditioner,
      cashbackOrderDetailEntityMapper,
      cashbackOrderMemberEntityMapper,
      smilecardCashbackOrderEntityMapper,
      smilecardT2T3CashbackEntityMapper
  )

  def "저장 호출이 잘 되는지 확인"() {
    when:
    repository.save(payCashback)

    then:
    cashbackOrderInvokeCount * cashbackOrderRepository.save(_ as CashbackOrderEntity) >> {}
    policyInvokeCount * cashbackOrderPolicyRepository.save(_ as CashbackOrderPolicyEntity) >> {}
    detailInvokeCount * cashbackOrderDetailRepository.save(_ as CashbackOrderDetailEntity) >> {}
    memberInvokeCount * cashbackOrderMemberRepository.save(_ as CashbackOrderMemberEntity) >> {}
    smileCardInvokeCount * smilecardCashbackOrderRepository.save(_ as SmilecardCashbackOrderEntity) >> {}
    t2t3InvokeCount * smilecardT2T3CashbackRepository.save(_ as SmilecardT2T3CashbackEntity) >> {}

    where:
    _________________________________________________
    desc | payCashback
    "저장대상이 없는 경우" | PayCashback_생성()
    "하나의 주문에 여러 캐시백이 적용된 경우" | PayCashback_생성(cashbacks: [Cashback_생성(cashbackUnits: [ItemCashback_생성(isSmilePay: true), SellerCashback_생성(amount: 1000L), SmilePayCashback_생성(isSmilePay: true), ChargePayCashback_생성(isChargePay: true), ClubDayCashback_생성(isSmilePay: true, isClubMember: true)])])
    "여러 주문에 여러 캐시백이 적용된 경우" | PayCashback_생성(cashbacks: [Cashback_생성(cashbackUnits: [ItemCashback_생성(isSmilePay: true)]), Cashback_생성(orderNo: 2L, cashbackUnits: [SellerCashback_생성(amount: 1000L)])])
    "스마일카드 캐시백이 적용된 경우" | PayCashback_생성(smileCardCashback: SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true))
    "T2T3스마일카드 캐시백이 적용된 경우" | PayCashback_생성(smileCardCashback: SmileCardCashback_생성(cashbackAmount: 1000L, isSmileCard: true, t2t3Cashbacks: [T2T3SmileCardCashback_생성(amount: 1000L, isT2T3: true)]))
    _________________________________________________
    cashbackOrderInvokeCount | policyInvokeCount | detailInvokeCount
    0 | 0 | 0
    5 | 5 | 1
    2 | 2 | 2
    0 | 0 | 0
    0 | 0 | 0
    _________________________________________________
    memberInvokeCount | smileCardInvokeCount | t2t3InvokeCount
    1 | 0 | 0
    1 | 0 | 0
    1 | 0 | 0
    1 | 1 | 0
    1 | 1 | 1
  }

  def "CashbackOrderPolicyEntity 변환이 잘되는지 확인"() {
    expect:
    def result = repository.mapToPolicies(payCashback, cashback, cashback.findAppliedCashbackPolicies())
    result == expectResult

    where:
    _________________________________________________
    desc | payCashback | cashback
    "적용대상이 아닐 경우 미생성" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ItemCashback_생성(), SellerCashback_생성(), SmilePayCashback_생성(), ChargePayCashback_생성(), ClubDayCashback_생성()])
    "아이템캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ItemCashback_생성(isSmilePay: true, maxLimitMoney: 1L)])
    "판매자캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [SellerCashback_생성(amount: 1000L)])
    "스마일페이캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [SmilePayCashback_생성(isSmilePay: true, saveRate: 1L)])
    "자동충전캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ChargePayCashback_생성(isChargePay: true, chargePaySaveRate: 1L, chargePayClubSaveRate: 2L, chargePayMaxMoney: 3L, chargePayClubMaxMoney: 4L)])
    "클럽데이캐시백" | PayCashback_생성() | Cashback_생성(cashbackUnits: [ClubDayCashback_생성(isSmilePay: true, isClubMember: true, clubDayMaxSaveMoney: 1L, clubDayMaxSaveRate: 2L)])
    _________________________________________________
    expectResult | _ | _
    [] | _ | _
    [CashbackOrderPolicyEntity_생성(policyNo: 1L, maxLimitMoney: 1L)] | _ | _
    [CashbackOrderPolicyEntity_생성(type: "S", policyNo: 0L, name: "판매자 제공 적립")] | _ | _
    [CashbackOrderPolicyEntity_생성(type: "P", policyNo: 1L, subType: "P", saveRate: 1L, maxLimitMoney: 0L)] | _ | _
    [CashbackOrderPolicyEntity_생성(type: "A", policyNo: 1L, subType: "P", maxLimitMoney: 0L, chargePaySaveRate: 1L, chargePayClubSaveRate: 2L, chargePayMaxMoney: 3L, chargePayClubMaxMoney: 4L)] | _ | _
    [CashbackOrderPolicyEntity_생성(type: "D", policyNo: 1L, subType: "P", maxLimitMoney: 0L, clubDayMaxSaveMoney: 1L, clubDayMaxSaveRate: 2L)] | _ | _
  }

  def "isDuplicatedCashback 중복 조회 확인"() {
    when:
    cashbackOrderDetailRepository.findById(_ as Long) >> condition

    then:
    def result = repository.isDuplicatedCashback(KeyMap_생성())
    result == expectedResult

    where:
    _________________________________________________
    desc | condition | expectedResult
    "조회 데이터가 있을 경우" | Optional.of(CashbackOrderDetailEntity_생성()) | true
    "조회 테이터가 없을 경우" | Optional.empty() | false
  }
}
