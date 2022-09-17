package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.RuleName;

public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
