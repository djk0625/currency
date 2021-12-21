package com.exam.calculator.service;

import com.exam.calculator.dto.CurrencyRequestDto;
import com.exam.calculator.dto.CurrencyResponseDto;
import com.exam.calculator.en.CountryEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeServiceTest {

    @SpyBean
    ExchangeService exchangeService;

    @Test
    void testGetCurrencyAPIRequest() {
        //Given
        CurrencyRequestDto currencyRequestDto = new CurrencyRequestDto(CountryEnum.USD);

        //When
        CurrencyResponseDto currencyResponseDto = exchangeService.getCurrencyRequest(currencyRequestDto);

        //Then
        assertEquals(currencyResponseDto.isSuccess(), true);
    }
}
