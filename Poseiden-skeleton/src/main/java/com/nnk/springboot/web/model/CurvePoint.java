package com.nnk.springboot.web.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is the POJO class for the CurvePoint Entity
 * 
 * @author Rémi Deronzier
 */
@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class CurvePoint {

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(nullable = false)
    private int curveId;

    @Column
    private Timestamp asOfDate;

    @Column(nullable = false)
    private Double term;

    @Column(nullable = false)
    private Double value;

    @Column
    private Timestamp creationDate;

}
