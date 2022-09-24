package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.Rating;

/**
 * This interface allows to communicate with the DB only for the Rating Entity
 * 
 * @author Rémi Deronzier
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
