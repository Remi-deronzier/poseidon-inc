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

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class BidList {

    public BidList(String account, String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int BidListId;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double bidQuantity;

    @Column()
    private Double askQuantity;

    @Column()
    private Double bid;

    @Column()
    private Double ask;

    @Column()
    private String benchmark;

    @Column()
    private Timestamp bidListDate;

    @Column()
    private String commentary;

    @Column()
    private String security;

    @Column()
    private String status;

    @Column()
    private String trader;

    @Column()
    private String book;

    @Column()
    private Timestamp creationDate;

    @Column()
    private Timestamp revisionDate;

    @Column()
    private String dealType;

    @Column()
    private String sourceListId;

    @Column()
    private String side;

}
