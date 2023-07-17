package com.abdi.cardiscover.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.ImageEntity;


@Repository

public interface ImageRepository extends CrudRepository<ImageEntity, Long>{
    Iterable<ImageEntity> findAll();
    Optional<ImageEntity> findById(Long id);
}
