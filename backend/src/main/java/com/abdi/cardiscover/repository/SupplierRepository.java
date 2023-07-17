package com.abdi.cardiscover.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.SupplierEntity;

@Repository
public interface SupplierRepository extends CrudRepository<SupplierEntity, Long> {
    SupplierEntity findByName(String name);
}

