package com.abdi.cardiscover.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.SpecialtyClassEntity;

@Repository
public interface SpecialtyClassRepository extends CrudRepository<SpecialtyClassEntity, Long> {
}
