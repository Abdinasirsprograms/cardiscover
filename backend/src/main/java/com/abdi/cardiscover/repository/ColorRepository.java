package com.abdi.cardiscover.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.ColorEntity;

@Repository
public interface ColorRepository extends CrudRepository<ColorEntity, Long> {
    ColorEntity findByColor(String color);
}