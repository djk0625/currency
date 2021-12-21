package com.exam.calculator.dto;

import com.exam.calculator.en.CountryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyRequestDto {
    private CountryEnum source;
}
