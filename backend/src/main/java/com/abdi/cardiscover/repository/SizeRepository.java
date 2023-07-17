package com.abdi.cardiscover.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.SizeEntity;

@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Long> {
    SizeEntity findByName(String name);
}
