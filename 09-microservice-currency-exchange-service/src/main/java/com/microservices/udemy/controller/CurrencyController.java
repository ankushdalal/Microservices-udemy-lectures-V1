package com.microservices.udemy.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.udemy.bean.CurrencyExchange;
import com.microservices.udemy.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {

//		CurrencyExchange currencyExchange = new CurrencyExchange(1000l, "USD", "INR", BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(from, to);
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
