package com.microservices.mini.project.currencyconversionservice.controller;

import com.microservices.mini.project.currencyconversionservice.feignproxy.FeignProxy;
import com.microservices.mini.project.currencyconversionservice.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class AppController {

    @Autowired
    private FeignProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
               quantity.multiply(currencyConversion.getConversionMultiple()), quantity, currencyConversion.getEnvironment());
    }
}
