package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> getBidLists() {
        return bidListRepository.findAll();
    }

    public BidList saveBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public void deleteBidList(int id) {
        bidListRepository.deleteById(id);
    }
}
