package com.nnk.springboot.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.service.RuleNameService;
import com.nnk.springboot.web.dto.RuleNameDto;
import com.nnk.springboot.web.model.RuleName;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        List<RuleName> ruleNames = ruleNameService.findAll();
        log.info("RuleName retrieved with length = {}", ruleNames.size());
        List<RuleNameDto> ruleNameDtos = ruleNames.stream().map(ruleName -> convertToDto(ruleName))
                .collect(Collectors.toList());
        model.addAttribute("ruleNameDtos", ruleNameDtos);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleNameDto ruleNameDto) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleNameDto ruleNameDto, BindingResult result, Model model) {
        log.info("Valid RuleName object");
        if (!result.hasErrors()) {
            log.info("Invalid RuleName object");
            RuleName ruleName = convertToEntity(ruleNameDto);
            ruleNameService.save(ruleName);
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        log.info("RuleName with id {} retrieved", id);
        RuleNameDto ruleNameDto = convertToDto(ruleName);
        model.addAttribute("ruleNameDto", ruleNameDto);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleNameDto ruleNameDto,
            BindingResult result, Model model) {
        log.info("Valid RuleName object");
        if (result.hasErrors()) {
            log.info("Invalid RuleName object");
            return "ruleName/update";
        }
        RuleName ruleName = convertToEntity(ruleNameDto);
        ruleNameService.save(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.delete(id);
        log.info("RuleName with id {} successfully deleted", id);
        return "redirect:/ruleName/list";
    }

    private RuleNameDto convertToDto(RuleName entity) {
        RuleNameDto dto = new RuleNameDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getJson(),
                entity.getJson(), entity.getSqlStr(), entity.getSqlPart());
        return dto;
    }

    private RuleName convertToEntity(RuleNameDto dto) {
        RuleName ruleName = new RuleName(dto.getName(), dto.getDescription(), dto.getJson(), dto.getTemplate(),
                dto.getSqlStr(), dto.getSqlPart());
        if (!StringUtils.isEmpty(dto.getId())) {
            ruleName.setId(dto.getId());
        }
        return ruleName;
    }
}
