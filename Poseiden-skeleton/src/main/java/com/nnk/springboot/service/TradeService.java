package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.TradeRepository;
import com.nnk.springboot.web.model.Trade;

/**
 * This class contains the business logic for the Trade Entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * @return List<Trade>
     */
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    /**
     * @param id
     * @return Trade
     */
    public Trade findById(int id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
    }

    /**
     * @param trade
     * @return Trade
     */
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        Optional<Trade> optionalCurvePoint = tradeRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            tradeRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Trade Id:" + id);
        }
    }

}
