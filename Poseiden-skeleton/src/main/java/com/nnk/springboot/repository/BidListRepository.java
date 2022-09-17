package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.BidList;

public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
