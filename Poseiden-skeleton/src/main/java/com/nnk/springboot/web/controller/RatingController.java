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

import com.nnk.springboot.service.RatingService;
import com.nnk.springboot.web.dto.RatingDto;
import com.nnk.springboot.web.model.Rating;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is the controller class for the Rating Entity
 * 
 * @author Rémi Deronzier
 */
@Controller
@Slf4j
public class RatingController {
    @Autowired
    private RatingService ratingService;

    /**
     * @param model
     * @return String
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        List<Rating> ratings = ratingService.findAll();
        log.info("Ratings retrieved with length = {}", ratings.size());
        List<RatingDto> ratingDtos = ratings.stream().map(rating -> convertToDto(rating))
                .collect(Collectors.toList());
        model.addAttribute("ratingDtos", ratingDtos);
        return "rating/list";
    }

    /**
     * @param model
     * @return String
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        model.addAttribute("ratingDto", new RatingDto(null, "", "", "", 0));
        return "rating/add";
    }

    /**
     * @param ratingDto
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid RatingDto ratingDto, BindingResult result, Model model) {
        log.info("Valid Rating object");
        if (!result.hasErrors()) {
            log.info("Invalid Rating object");
            Rating rating = convertToEntity(ratingDto);
            ratingService.save(rating);
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    /**
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        log.info("Rating with id {} retrieved", id);
        RatingDto ratingDto = convertToDto(rating);
        model.addAttribute("ratingDto", ratingDto);
        return "rating/update";
    }

    /**
     * @param id
     * @param ratingDto
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid RatingDto ratingDto,
            BindingResult result, Model model) {
        log.info("Valid Rating object");
        if (result.hasErrors()) {
            log.info("Invalid Rating object");
            return "rating/update";
        }
        Rating rating = convertToEntity(ratingDto);
        ratingService.save(rating);
        return "redirect:/rating/list";
    }

    /**
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.delete(id);
        log.info("Rating with id {} successfully deleted", id);
        return "redirect:/rating/list";
    }

    /**
     * @param entity
     * @return RatingDto
     */
    private RatingDto convertToDto(Rating entity) {
        RatingDto dto = new RatingDto(entity.getId(), entity.getMoodysRating(), entity.getSandPRating(),
                entity.getFitchRating(), entity.getOrderNumber());
        return dto;
    }

    /**
     * @param dto
     * @return Rating
     */
    private Rating convertToEntity(RatingDto dto) {
        Rating rating = new Rating(dto.getMoodysRating(), dto.getSandPRating(), dto.getFitchRating(),
                dto.getOrderNumber());
        if (!StringUtils.isEmpty(dto.getId())) {
            rating.setId(dto.getId());
        }
        return rating;
    }

}
