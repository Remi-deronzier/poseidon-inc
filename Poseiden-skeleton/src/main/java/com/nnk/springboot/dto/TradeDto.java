package com.nnk.springboot.dto;

import lombok.Data;

@Data
public class TradeDto {

    public TradeDto(int tradeId, String account, String type) {
        this.tradeId = tradeId;
        this.account = account;
        this.type = type;
    }

    private int tradeId;
    private String account;
    private String type;

}