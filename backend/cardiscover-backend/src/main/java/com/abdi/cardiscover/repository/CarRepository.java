package com.abdi.cardiscover.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.car.CarEntity;


@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {
    Iterable<CarEntity> findAll();
    Optional<CarEntity> findById(Long Id);
}