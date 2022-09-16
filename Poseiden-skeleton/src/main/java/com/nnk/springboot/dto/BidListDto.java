package com.nnk.springboot.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class BidListDto {

    public BidListDto(String account, String type, double bidQuantity, Integer BidListId) {
        this.BidListId = BidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    private Integer BidListId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Positive(message = "Bid quantity must be a number strictly greater than 0")
    private Double bidQuantity;
}
