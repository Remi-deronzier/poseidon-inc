package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.CurvePoint;

/**
 * This interface allows to communicate with the DB only for the CurvePoint
 * Entity
 * 
 * @author Rémi Deronzier
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
