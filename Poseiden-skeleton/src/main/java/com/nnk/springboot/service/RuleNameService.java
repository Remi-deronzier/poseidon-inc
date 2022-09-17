package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.RuleNameRepository;
import com.nnk.springboot.web.model.RuleName;

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
