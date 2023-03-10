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

import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.CashbackOrderPolicyEntity_??????
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ChargePayCashback_??????
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ClubDayCashback_??????
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.ItemCashback_??????
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SellerCashback_??????
import static com.ebaykorea.payback.grocery.CashbackUnitGrocery.SmilePayCashback_??????
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.Cashback_??????
import static com.ebaykorea.payback.grocery.PayCashbackGrocery.PayCashback_??????
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.SmileCardCashback_??????
import static com.ebaykorea.payback.grocery.SmileCardCashbackGrocery.T2T3SmileCardCashback_??????
import static com.ebaykorea.payback.grocery.CashbackEntityGrocery.CashbackOrderDetailEntity_??????
import static com.ebaykorea.payback.grocery.OrderGrocery.KeyMap_??????

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

  def "?????? ????????? ??? ????????? ??????"() {
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
    "??????????????? ?????? ??????" | PayCashback_??????()
    "????????? ????????? ?????? ???????????? ????????? ??????" | PayCashback_??????(cashbacks: [Cashback_??????(cashbackUnits: [ItemCashback_??????(isSmilePay: true), SellerCashback_??????(amount: 1000L), SmilePayCashback_??????(isSmilePay: true), ChargePayCashback_??????(isChargePay: true), ClubDayCashback_??????(isSmilePay: true, isClubMember: true)])])
    "?????? ????????? ?????? ???????????? ????????? ??????" | PayCashback_??????(cashbacks: [Cashback_??????(cashbackUnits: [ItemCashback_??????(isSmilePay: true)]), Cashback_??????(orderNo: 2L, cashbackUnits: [SellerCashback_??????(amount: 1000L)])])
    "??????????????? ???????????? ????????? ??????" | PayCashback_??????(smileCardCashback: SmileCardCashback_??????(cashbackAmount: 1000L, isSmileCard: true))
    "T2T3??????????????? ???????????? ????????? ??????" | PayCashback_??????(smileCardCashback: SmileCardCashback_??????(cashbackAmount: 1000L, isSmileCard: true, t2t3Cashbacks: [T2T3SmileCardCashback_??????(amount: 1000L, isT2T3: true)]))
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

  def "CashbackOrderPolicyEntity ????????? ???????????? ??????"() {
    expect:
    def result = repository.mapToPolicies(payCashback, cashback, cashback.findAppliedCashbackPolicies())
    result == expectResult

    where:
    _________________________________________________
    desc | payCashback | cashback
    "??????????????? ?????? ?????? ?????????" | PayCashback_??????() | Cashback_??????(cashbackUnits: [ItemCashback_??????(), SellerCashback_??????(), SmilePayCashback_??????(), ChargePayCashback_??????(), ClubDayCashback_??????()])
    "??????????????????" | PayCashback_??????() | Cashback_??????(cashbackUnits: [ItemCashback_??????(isSmilePay: true, maxLimitMoney: 1L)])
    "??????????????????" | PayCashback_??????() | Cashback_??????(cashbackUnits: [SellerCashback_??????(amount: 1000L)])
    "????????????????????????" | PayCashback_??????() | Cashback_??????(cashbackUnits: [SmilePayCashback_??????(isSmilePay: true, saveRate: 1L)])
    "?????????????????????" | PayCashback_??????() | Cashback_??????(cashbackUnits: [ChargePayCashback_??????(isChargePay: true, chargePaySaveRate: 1L, chargePayClubSaveRate: 2L, chargePayMaxMoney: 3L, chargePayClubMaxMoney: 4L)])
    "?????????????????????" | PayCashback_??????() | Cashback_??????(cashbackUnits: [ClubDayCashback_??????(isSmilePay: true, isClubMember: true, clubDayMaxSaveMoney: 1L, clubDayMaxSaveRate: 2L)])
    _________________________________________________
    expectResult | _ | _
    [] | _ | _
    [CashbackOrderPolicyEntity_??????(policyNo: 1L, maxLimitMoney: 1L)] | _ | _
    [CashbackOrderPolicyEntity_??????(type: "S", policyNo: 0L, name: "????????? ?????? ??????")] | _ | _
    [CashbackOrderPolicyEntity_??????(type: "P", policyNo: 1L, subType: "P", saveRate: 1L, maxLimitMoney: 0L)] | _ | _
    [CashbackOrderPolicyEntity_??????(type: "A", policyNo: 1L, subType: "P", maxLimitMoney: 0L, chargePaySaveRate: 1L, chargePayClubSaveRate: 2L, chargePayMaxMoney: 3L, chargePayClubMaxMoney: 4L)] | _ | _
    [CashbackOrderPolicyEntity_??????(type: "D", policyNo: 1L, subType: "P", maxLimitMoney: 0L, clubDayMaxSaveMoney: 1L, clubDayMaxSaveRate: 2L)] | _ | _
  }

  def "isDuplicatedCashback ?????? ?????? ??????"() {
    when:
    cashbackOrderDetailRepository.findById(_ as Long) >> condition

    then:
    def result = repository.isDuplicatedCashback(KeyMap_??????())
    result == expectedResult

    where:
    _________________________________________________
    desc | condition | expectedResult
    "?????? ???????????? ?????? ??????" | Optional.of(CashbackOrderDetailEntity_??????()) | true
    "?????? ???????????? ?????? ??????" | Optional.empty() | false
  }
}
