package com.ebaykorea.payback.core.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstallmentType {
    Default("N"),
    InterestOnGmkt("C"),
    InterestFree("F");

    private final String code;


}
