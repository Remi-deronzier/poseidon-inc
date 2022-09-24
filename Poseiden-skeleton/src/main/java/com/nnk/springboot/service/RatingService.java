package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.RatingRepository;
import com.nnk.springboot.web.model.Rating;

/**
 * This class contains the business logic for the Rating Entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * @return List<Rating>
     */
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    /**
     * @param id
     * @return Rating
     */
    public Rating findById(int id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
    }

    /**
     * @param rating
     * @return Rating
     */
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        Optional<Rating> optionalCurvePoint = ratingRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            ratingRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Rating Id:" + id);
        }
    }

}
