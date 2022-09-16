package com.nnk.springboot.dto;

import lombok.Data;

@Data
public class CurvePointDto {

    public CurvePointDto(int id, int curveId, double term, double value) {
        this.id = id;
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    private int id;
    private int curveId;
    private Double term;
    private Double value;

}
