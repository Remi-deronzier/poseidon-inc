package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.Trade;

/**
 * This interface allows to communicate with the DB only for the Trade Entity
 * 
 * @author Rémi Deronzier
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
