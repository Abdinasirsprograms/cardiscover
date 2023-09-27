package com.abdi.cardiscover.repository;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdi.cardiscover.entity.ReservationEntity;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
    ReservationEntity findByPickupTime(GregorianCalendar pickupTime);
    ReservationEntity findByDropoffTime(GregorianCalendar dropoffTime);
    List<ReservationEntity> findAll();
    Optional<ReservationEntity> findById(Long id);
    void deleteById(Long id);
    void deleteAll();
}

