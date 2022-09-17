package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.RatingRepository;
import com.nnk.springboot.web.model.Rating;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating findById(int id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public void delete(int id) {
        Optional<Rating> optionalCurvePoint = ratingRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            ratingRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Rating Id:" + id);
        }
    }

}
