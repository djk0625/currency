package com.exam.calculator.service;

import com.exam.calculator.dto.CountryDto;
import com.exam.calculator.dto.CurrencyRequestDto;
import com.exam.calculator.dto.CurrencyResponseDto;
import com.exam.calculator.en.CountryEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

    // 송금 국가 미국으로 고정
    public CountryDto getSource() {
        return new CountryDto(CountryEnum.USD.getMark(), CountryEnum.USD.name());
    }

    // 수취 국가 설정
    public List<CountryDto> getCountries() {
        CountryDto korea = new CountryDto(CountryEnum.KRW.getMark(), CountryEnum.KRW.name());
        CountryDto japan = new CountryDto(CountryEnum.JPY.getMark(), CountryEnum.JPY.name());
        CountryDto philippines = new CountryDto(CountryEnum.PHP.getMark(), CountryEnum.PHP.name());

        List<CountryDto> countries = new ArrayList<>();
        countries.add(korea);
        countries.add(japan);
        countries.add(philippines);

        return countries;
    }

    // 환율 변수 선언
    public String getRates(CountryEnum source) {
        CountryEnum[] countryEnums = CountryEnum.values();

        String rates = Arrays.stream(countryEnums)
                .filter(e -> !e.equals(source))
                .map(Enum::name)
                .collect(Collectors.joining(","));

        return rates;
    }

    // 환율 요청
    public CurrencyResponseDto getCurrencyRequest(CurrencyRequestDto param) {
        String baseURL = "http://apilayer.net/api/live";
        String accessKey = "25b2ee8748bd823b6c9d8e0b7e2b0c4b";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(baseURL)
                .queryParam("accessKey", accessKey)
                .queryParam("source", param.getSource())
                .queryParam("rates", getRates(param.getSource()))
                .queryParam("format", 1);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        HttpEntity<String> httpResponse = new RestTemplate().exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, httpEntity, String.class);

        // 결과값
        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyResponseDto result = null;
        try {
            result = objectMapper.readValue(httpResponse.getBody(), CurrencyResponseDto.class);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}