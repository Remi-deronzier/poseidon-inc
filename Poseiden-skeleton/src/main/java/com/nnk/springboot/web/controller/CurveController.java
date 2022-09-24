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

import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.web.dto.CurvePointDto;
import com.nnk.springboot.web.model.CurvePoint;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is the controller class for the CurvePoint Entity
 * 
 * @author Rémi Deronzier
 */
@Controller
@Slf4j
public class CurveController {
    @Autowired
    private CurvePointService curvePointService;

    /**
     * @param model
     * @return String
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        List<CurvePoint> curvePoints = curvePointService.findAll();
        log.info("CurvePoints retrieved with length = {}", curvePoints.size());
        List<CurvePointDto> curvePointDtos = curvePoints.stream().map(curvePoint -> convertToDto(curvePoint))
                .collect(Collectors.toList());
        model.addAttribute("curvePointDtos", curvePointDtos);
        return "curvePoint/list";
    }

    /**
     * @param model
     * @return String
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {
        model.addAttribute("curvePointDto", new CurvePointDto(null, 0, 0, 0));
        return "curvePoint/add";
    }

    /**
     * @param curvePointDto
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDto curvePointDto, BindingResult result, Model model) {
        log.info("Valid CurvePoint object");
        if (!result.hasErrors()) {
            log.info("Invalid CurvePoint object");
            CurvePoint curvePoint = convertToEntity(curvePointDto);
            curvePointService.save(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    /**
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        log.info("CurvePoint with id {} retrieved", id);
        CurvePointDto curvePointDto = convertToDto(curvePoint);
        model.addAttribute("curvePointDto", curvePointDto);
        return "curvePoint/update";
    }

    /**
     * @param id
     * @param curvePointDto
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePointDto curvePointDto,
            BindingResult result, Model model) {
        log.info("Valid CurvePoint object");
        if (result.hasErrors()) {
            log.info("Invalid CurvePoint object");
            return "curvePoint/update";
        }
        CurvePoint curvePoint = convertToEntity(curvePointDto);
        curvePointService.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.delete(id);
        log.info("CurvePoint with id {} successfully deleted", id);
        return "redirect:/curvePoint/list";
    }

    /**
     * @param entity
     * @return CurvePointDto
     */
    private CurvePointDto convertToDto(CurvePoint entity) {
        CurvePointDto dto = new CurvePointDto(entity.getId(), entity.getCurveId(), entity.getTerm(), entity.getValue());
        return dto;
    }

    /**
     * @param dto
     * @return CurvePoint
     */
    private CurvePoint convertToEntity(CurvePointDto dto) {
        CurvePoint curvePoint = new CurvePoint(dto.getCurveId(), dto.getTerm(), dto.getValue());
        if (!StringUtils.isEmpty(dto.getId())) {
            curvePoint.setId(dto.getId());
        }
        return curvePoint;
    }
}
