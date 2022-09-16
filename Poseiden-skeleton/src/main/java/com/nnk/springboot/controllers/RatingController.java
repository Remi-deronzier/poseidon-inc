package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;

@Controller
public class RatingController {
    // TODO: Inject Rating service

    @RequestMapping("/rating/list")
    public String home(Model model) {
        // TODO: find all Rating, add to model
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and
        // return Rating list
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        return "redirect:/rating/list";
    }

    private RatingDto convertToDto(Rating entity) {
        RatingDto dto = new RatingDto(entity.getId(), entity.getMoodysRating(), entity.getSandPRating(),
                entity.getFitchRating(), entity.getOrderNumber());
        return dto;
    }

    private Rating convertToEntity(RatingDto dto) {
        Rating rating = new Rating(dto.getMoodysRating(), dto.getSandPRating(), dto.getFitchRating(),
                dto.getOrderNumber());
        if (!StringUtils.isEmpty(dto.getId())) {
            rating.setId(dto.getId());
        }
        return rating;
    }

}
