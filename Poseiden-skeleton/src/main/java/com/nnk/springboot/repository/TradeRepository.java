package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
