package com.abdi.cardiscover.repository;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.RateEntity;

@Repository
public interface RateRepository extends CrudRepository<RateEntity, Long> {
    RateEntity findByRate(BigDecimal rate);
}

