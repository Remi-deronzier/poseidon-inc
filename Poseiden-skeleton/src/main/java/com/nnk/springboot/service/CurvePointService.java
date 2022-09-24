package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.CurvePointRepository;
import com.nnk.springboot.web.model.CurvePoint;

/**
 * This class contains the business logic for the CurvePoint Entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * @return List<CurvePoint>
     */
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    /**
     * @param id
     * @return CurvePoint
     */
    public CurvePoint findById(int id) {
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Curve point Id:" + id));
    }

    /**
     * @param curvePoint
     * @return CurvePoint
     */
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        Optional<CurvePoint> optionalCurvePoint = curvePointRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            curvePointRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid Curve point Id:" + id);
        }
    }

}
