package com.abdi.cardiscover.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.LocationEntity;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {
    LocationEntity findByName(String name);
    List<LocationEntity> findByNameStartingWith(String name);
}
