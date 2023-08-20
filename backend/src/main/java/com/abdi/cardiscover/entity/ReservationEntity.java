package com.abdi.cardiscover.entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class ReservationEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private GregorianCalendar pickupTime;
    private GregorianCalendar dropoffTime;
    @ManyToMany(mappedBy = "reservations")
    private List<CarEntity> cars = new ArrayList<>();
    public ReservationEntity(){}
    public ReservationEntity(GregorianCalendar pickupTime, GregorianCalendar dropoffTime){
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AvailabilityEntity [dropOffTime=" + pickupTime + ", pickUpTime=" + dropoffTime + ", car=" + cars + "]";
    }
    public GregorianCalendar getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(GregorianCalendar dropOffTime) {
        this.pickupTime = dropOffTime;
    }
    public GregorianCalendar getDropoffTime() {
        return dropoffTime;
    }
    public void setDropoffTime(GregorianCalendar pickUpTime) {
        this.dropoffTime = pickUpTime;
    }
    public List<CarEntity> getCars() {
        return cars;
    }
    public CarEntity getCar() {
        return cars.get(0);
    }
    public void setCar(CarEntity car) {
        this.cars.add(car);
    }

}
