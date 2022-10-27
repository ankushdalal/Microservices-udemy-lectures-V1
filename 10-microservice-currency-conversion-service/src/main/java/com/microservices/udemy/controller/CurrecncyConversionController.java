package com.microservices.udemy.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.udemy.bean.CurrencyConversion;
import com.microservices.udemy.feign.proxy.CurrencyConversionProxy;

@RestController
public class CurrecncyConversionController {

	@Autowired(required = true)
	private CurrencyConversionProxy currencyConversionProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getCurr_Conversion(@PathVariable String from, @PathVariable String to,
			@PathVariable String quantity) {

		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> currencyConversionEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion = currencyConversionEntity.getBody();

		System.out.println(
				currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(Long.parseLong(quantity))));
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
				quantity,
				currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(Long.parseLong(quantity))),
				currencyConversion.getEnvironment());

	}
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion currConversionFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable String quantity) {

		CurrencyConversion currencyConversion = currencyConversionProxy.getCurrConversionFeign(from, to, quantity);
		
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
				quantity,
				currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(Long.parseLong(quantity))),
				currencyConversion.getEnvironment()+" "+"feign");

	}
}
