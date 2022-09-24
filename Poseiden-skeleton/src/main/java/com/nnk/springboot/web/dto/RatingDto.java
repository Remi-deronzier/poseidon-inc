package com.nnk.springboot.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Data;

/**
 * This class is the DTO class for the Rating Entity
 * 
 * @author Rémi Deronzier
 */
@Data
public class RatingDto {

    public RatingDto(Integer id, String moodysRating, String sandPRating, String fitchRating,
            int orderNumber) {
        this.id = id;
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    private Integer id;

    @NotBlank(message = "Moodys rating must not be blank")
    private String moodysRating;

    @NotBlank(message = "Sand rating must not be blank")
    private String sandPRating;

    @NotBlank(message = "Fitch rating must not be blank")
    private String fitchRating;

    @Positive(message = "Order number must be strictly positive")
    private int orderNumber;

}