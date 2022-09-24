package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.BidList;

/**
 * This interface allows to communicate with the DB only for the BidList Entity
 * 
 * @author Rémi Deronzier
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
