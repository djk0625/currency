package com.exam.calculator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponseDto {
    private boolean success;
    private String terms;
    private String privacy;
    private Integer timestamp;
    private String source;
    private Map<String, String> quotes;
}
