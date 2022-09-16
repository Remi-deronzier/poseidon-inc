package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Data;

@Entity
@Data
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BidListId;
    @Column(nullable = false)
    @NotBlank(message = "Account is mandatory")
    private String account;
    @Column(nullable = false)
    @NotBlank(message = "Type is mandatory")
    private String type;
    @Column(nullable = false)
    @NotBlank(message = "Bid quantity is mandatory")
    @Positive(message = "Bid quantity must be a number strictyl greater than 0")
    private Double bidQuantity;
    @Column
    private Double askQuantity;
    @Column
    private Double bid;
    @Column
    private Double ask;
    @Column
    private String benchmark;
    @Column
    private Timestamp bidListDate;
    @Column
    private String commentary;
    @Column
    private String security;
    @Column
    private String status;
    @Column
    private String trader;
    @Column
    private String book;
    @Column
    private String creationName;
    @Column
    private Timestamp creationDate;
    @Column
    private String revisionName;
    @Column
    private Timestamp revisionDate;
    @Column
    private String dealName;
    @Column
    private String dealType;
    @Column
    private String sourceListId;
    @Column
    private String side;
}
