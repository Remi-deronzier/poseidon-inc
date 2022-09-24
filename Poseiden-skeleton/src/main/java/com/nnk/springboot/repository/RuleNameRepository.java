package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.RuleName;

/**
 * This interface allows to communicate with the DB only for the RuleName Entity
 * 
 * @author Rémi Deronzier
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
