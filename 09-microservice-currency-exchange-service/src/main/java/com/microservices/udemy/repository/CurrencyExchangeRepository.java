package com.microservices.udemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.udemy.bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	public CurrencyExchange findByFromAndTo(String from, String to);

}
