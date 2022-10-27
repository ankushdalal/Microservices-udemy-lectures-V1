package com.microservices.udemy.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.udemy.bean.CurrencyConversion;

@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface CurrencyConversionProxy {

	@RequestMapping(method=RequestMethod.GET,value="/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getCurrConversionFeign(@RequestParam String from, @RequestParam String to,
			@RequestParam String quantity);

}
