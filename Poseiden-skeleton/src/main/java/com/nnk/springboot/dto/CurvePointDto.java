package com.nnk.springboot.dto;

import javax.validation.constraints.Digits;

import lombok.Data;

@Data
public class CurvePointDto {

    public CurvePointDto(Integer id, Integer curveId, double term, double value) {
        this.id = id;
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    private Integer id;

    @Digits(fraction = 0, integer = 10, message = "Curve id must be an integer")
    private Integer curveId;

    @Digits(fraction = 2, integer = 10, message = "Term must be a number")
    private Double term;

    @Digits(fraction = 2, integer = 10, message = "Value must be a number")
    private Double value;

}
