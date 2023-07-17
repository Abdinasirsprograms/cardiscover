package com.abdi.cardiscover.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.brand.BrandEntity;

@Repository
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {
    BrandEntity findByName(String name);
}
