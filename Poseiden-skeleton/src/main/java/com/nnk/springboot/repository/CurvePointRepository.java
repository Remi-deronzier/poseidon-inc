package com.nnk.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.CurvePoint;

public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
