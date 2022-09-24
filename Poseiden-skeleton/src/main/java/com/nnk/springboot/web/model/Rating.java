package com.nnk.springboot.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is the POJO class for the Rating Entity
 * 
 * @author Rémi Deronzier
 */
@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class Rating {

    public Rating(String moodysRating, String sandPRating, String fitchRating,
            int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String moodysRating;

    @Column(nullable = false)
    private String sandPRating;

    @Column(nullable = false)
    private String fitchRating;

    @Column(nullable = false)
    private int orderNumber;

}
