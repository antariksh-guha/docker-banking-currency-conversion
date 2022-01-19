package com.microservices.mini.project.currencyexchangeservice.controller;

import com.microservices.mini.project.currencyexchangeservice.dao.CurrencyExchangeRepository;
import com.microservices.mini.project.currencyexchangeservice.exception.CurrencyNotFoundException;
import com.microservices.mini.project.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

//    @Retry
//    @CircuitBreaker(name = "default", fallbackMethod= "hardCoded")
//    @RateLimiter(name = "default")
//    @BulkHead(name = "default")
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to)
    {
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if(currencyExchange==null)
            throw new CurrencyNotFoundException("Unable to find currency data");
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }

//    public String hardCoded(Exception ex)
//    {
//        return "Fall Back"
//    }
}
