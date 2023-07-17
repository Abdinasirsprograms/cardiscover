package com.abdi.cardiscover.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.location.LocationEntity;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {
    LocationEntity findByName(String name);
}
