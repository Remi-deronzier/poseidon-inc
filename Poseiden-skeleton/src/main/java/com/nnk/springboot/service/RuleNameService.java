package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.RuleNameRepository;
import com.nnk.springboot.web.model.RuleName;

/**
 * This class contains the business logic for the RuleName Entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * @return List<RuleName>
     */
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    /**
     * @param id
     * @return RuleName
     */
    public RuleName findById(int id) {
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rule name Id:" + id));
    }

    /**
     * @param ruleName
     * @return RuleName
     */
    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        Optional<RuleName> optionalCurvePoint = ruleNameRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            ruleNameRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Rule name Id:" + id);
        }
    }

}
