package com.nnk.springboot.dto;

import lombok.Data;

@Data
public class BidListDto {

    public BidListDto(String account, String type, double bidQuantity, int BidListId) {
        this.BidListId = BidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    private int BidListId;
    private String account;
    private String type;
    private Double bidQuantity;
}
