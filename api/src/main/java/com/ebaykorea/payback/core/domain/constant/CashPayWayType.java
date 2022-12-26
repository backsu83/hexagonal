package com.ebaykorea.payback.core.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CashPayWayType {
    Unknown("", ""),
    Alipay("A", "알리페이"),
    DirectPayment("C", "판매자 직결제"),
    NewSmilePayEtc("E", "NewSmilePayEtc"),
    CashPayG("G", "GNewCash&NewSmilePayEPrePay"),
    NewSmilePayCash("H", "스마일페이 현금"),
    CashPayM("M", "NewSmilePayMobile&Phone&Card"),
    PayPal("P", "PayPal"),
    CashPayR("R", "스마일페이 카드&실시간계좌이체"),
    VirtualCash("V", "가상계좌"),
    SmileCashEvent("Y", "스마일캐시 이벤트성"),
    SmileCash("X", "스마일캐시 현금성");

    private String code;
    private String description;
}
