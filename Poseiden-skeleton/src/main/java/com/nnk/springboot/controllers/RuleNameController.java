package com.nnk.springboot.controllers;

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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        List<RuleName> ruleNames = ruleNameService.findAll();
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
        if (!result.hasErrors()) {
            RuleName ruleName = convertToEntity(ruleNameDto);
            ruleNameService.save(ruleName);
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        RuleNameDto ruleNameDto = convertToDto(ruleName);
        model.addAttribute("ruleNameDto", ruleNameDto);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleNameDto ruleNameDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        RuleName ruleName = convertToEntity(ruleNameDto);
        ruleNameService.save(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.delete(id);
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
