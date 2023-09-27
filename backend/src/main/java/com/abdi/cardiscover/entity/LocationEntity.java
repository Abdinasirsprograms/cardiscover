package com.abdi.cardiscover.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class LocationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CarEntity> cars;
    
    public Long getId() {
        return id;
    }
    public LocationEntity(){};
    public LocationEntity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<CarEntity> getCars() {
        return this.cars;
    }
    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }

    public void removeCar(CarEntity car){
        this.cars.remove(car);
    }

    public void addCar(CarEntity car) {
        if(this.cars == null){
            List<CarEntity> newCarsList = new ArrayList<>();
            this.cars = newCarsList;
            this.cars.add(car);
        }else{
            this.cars.add(car);
        }
    }
    @Override
    public String toString() {
        return "LocationEntity [id=" + id + ", name=" + name + "]";
    }
}
