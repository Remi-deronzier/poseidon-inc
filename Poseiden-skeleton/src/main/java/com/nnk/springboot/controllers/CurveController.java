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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.services.CurvePointService;

@Controller
public class CurveController {
    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        List<CurvePoint> curvePoints = curvePointService.findAll();
        List<CurvePointDto> curvePointDtos = curvePoints.stream().map(curvePoint -> convertToDto(curvePoint))
                .collect(Collectors.toList());
        model.addAttribute("curvePointDtos", curvePointDtos);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {
        model.addAttribute("curvePointDto", new CurvePointDto(null, 0, 0, 0));
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDto curvePointDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            CurvePoint curvePoint = convertToEntity(curvePointDto);
            curvePointService.save(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        CurvePointDto curvePointDto = convertToDto(curvePoint);
        model.addAttribute("curvePointDto", curvePointDto);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePointDto curvePointDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        CurvePoint curvePoint = convertToEntity(curvePointDto);
        curvePointService.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }

    private CurvePointDto convertToDto(CurvePoint entity) {
        CurvePointDto dto = new CurvePointDto(entity.getId(), entity.getCurveId(), entity.getTerm(), entity.getValue());
        return dto;
    }

    private CurvePoint convertToEntity(CurvePointDto dto) {
        CurvePoint curvePoint = new CurvePoint(dto.getCurveId(), dto.getTerm(), dto.getValue());
        if (!StringUtils.isEmpty(dto.getId())) {
            curvePoint.setId(dto.getId());
        }
        return curvePoint;
    }
}
