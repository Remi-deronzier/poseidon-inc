package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @NotBlank(message = "Curve id must not be null")
    @Digits(fraction = 0, integer = 10, message = "Curve id must be an integer")
    private int curveId;
    @Column
    private Timestamp asOfDate;
    @Column(nullable = false)
    @NotBlank(message = "Term must not be null")
    @Digits(fraction = 2, integer = 10, message = "Term must be a number")
    private Double term;
    @Column(nullable = false)
    @NotBlank(message = "Value must not be null")
    @Digits(fraction = 2, integer = 10, message = "Value must be a number")
    private Double value;
    @Column
    private Timestamp creationDate;
}
