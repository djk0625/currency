package com.exam.calculator.controller;

import com.exam.calculator.dto.CurrencyRequestDto;
import com.exam.calculator.dto.CurrencyResponseDto;
import com.exam.calculator.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    //환율 가져오기
    @GetMapping(value = "/getExchangeRate")
    public CurrencyResponseDto getExchangeRate(CurrencyRequestDto param) {

        return exchangeService.getCurrencyRequest(param);
    }

    //수취 금액 구하기
    @GetMapping(value = "/getAmount")
    public double getAmount(Integer money, Double rate) {

        return money * rate;
    }
}
