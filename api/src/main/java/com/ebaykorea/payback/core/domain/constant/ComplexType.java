package com.ebaykorea.payback.core.domain.constant;

import com.ebaykorea.payback.util.PaybackEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum ComplexType {
    Unknown(""),
    GCash("1"),
    DaumMileage("3"),
    OKCashbag("4"),
    CultureCash("5"),
    SmileCash("6"),
    SmileCashEvent("7"),
    LGUPlus("8");

    private String code;

    private static transient Map<String, ComplexType> map = PaybackEnums
            .reverseMap(ComplexType.class, ComplexType::getCode);

    public static ComplexType codeOf(final String code) {
        return map.getOrDefault(code.toUpperCase(), Unknown);
    }
}
