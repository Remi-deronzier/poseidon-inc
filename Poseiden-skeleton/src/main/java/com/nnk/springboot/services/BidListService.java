package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList findById(int id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Bid list Id:" + id));
    }

    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public void delete(int id) {
        Optional<BidList> optionalBidList = bidListRepository.findById(id);
        if (optionalBidList.isPresent()) {
            bidListRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Bid list Id:" + id);
        }
    }
}
