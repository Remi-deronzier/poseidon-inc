package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.BidListRepository;
import com.nnk.springboot.web.model.BidList;

/**
 * This class contains the business logic for the BidList Entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    /**
     * @return List<BidList>
     */
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    /**
     * @param id
     * @return BidList
     */
    public BidList findById(int id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Bid list Id:" + id));
    }

    /**
     * @param bidList
     * @return BidList
     */
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        Optional<BidList> optionalBidList = bidListRepository.findById(id);
        if (optionalBidList.isPresent()) {
            bidListRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Bid list Id:" + id);
        }
    }
}
