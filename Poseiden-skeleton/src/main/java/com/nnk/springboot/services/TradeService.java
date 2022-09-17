package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Trade findById(int id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
    }

    public Trade save(Trade curvePoint) {
        return tradeRepository.save(curvePoint);
    }

    public void delete(int id) {
        Optional<Trade> optionalCurvePoint = tradeRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            tradeRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Trade Id:" + id);
        }
    }

}
