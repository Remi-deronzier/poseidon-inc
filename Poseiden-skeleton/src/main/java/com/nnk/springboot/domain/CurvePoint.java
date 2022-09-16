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

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Data
@DynamicUpdate
@Table(name = "Curvepoint")
public class CurvePoint {

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "curveId", nullable = false)
    @NotBlank(message = "Curve id must not be null")
    @Digits(fraction = 0, integer = 10, message = "Curve id must be an integer")
    private int curveId;

    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    @Column(name = "term", nullable = false)
    @NotBlank(message = "Term must not be null")
    @Digits(fraction = 2, integer = 10, message = "Term must be a number")
    private Double term;

    @Column(name = "value", nullable = false)
    @NotBlank(message = "Value must not be null")
    @Digits(fraction = 2, integer = 10, message = "Value must be a number")
    private Double value;

    @Column(name = "creationDate")
    private Timestamp creationDate;

}
