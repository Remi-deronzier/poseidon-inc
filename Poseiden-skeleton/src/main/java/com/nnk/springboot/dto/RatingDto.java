package com.nnk.springboot.dto;

import lombok.Data;

@Data
public class RatingDto {

    public RatingDto(int id, String moodysRating, String sandPRating, String fitchRating,
            int orderNumber) {
        this.id = id;
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    private int id;
    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    private int orderNumber;
}