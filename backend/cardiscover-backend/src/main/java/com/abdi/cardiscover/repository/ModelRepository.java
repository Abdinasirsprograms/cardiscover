package com.abdi.cardiscover.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.ModelEntity;

@Repository
public interface ModelRepository extends CrudRepository<ModelEntity, Long> {
    ModelEntity findByName(String name);
}