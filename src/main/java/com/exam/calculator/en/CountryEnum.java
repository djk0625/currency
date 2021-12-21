package com.exam.calculator.en;

import lombok.Getter;

@Getter
public enum CountryEnum {
    KRW("한국")
	, USD("미국")
	, PHP("필리핀")
	, JPY("일본");

    private String mark;

    private CountryEnum(String mark) {
        this.mark = mark;
    }
}
