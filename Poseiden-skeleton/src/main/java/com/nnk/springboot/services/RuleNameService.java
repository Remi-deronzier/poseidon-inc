package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    public RuleName findById(int id) {
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rule name Id:" + id));
    }

    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public void delete(int id) {
        Optional<RuleName> optionalCurvePoint = ruleNameRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            ruleNameRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Rule name Id:" + id);
        }
    }

}
