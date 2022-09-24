package com.nnk.springboot.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Data;

/**
 * This class is the DTO class for the Trade Entity
 * 
 * @author Rémi Deronzier
 */
@Data
public class TradeDto {

    public TradeDto(Integer tradeId, String account, String type, Double buyQuantity) {
        this.tradeId = tradeId;
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }

    private Integer tradeId;

    @NotBlank(message = "Account must not be blank")
    private String account;

    @NotBlank(message = "Type must not be blank")
    private String type;

    @Positive(message = "Buy quantity must be strictly positive")
    private Double buyQuantity;

}