package com.abdi.cardiscover.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private LocationEntity location;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ModelEntity model;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ColorEntity color;
    // @ManyToOne(cascade = CascadeType.PERSIST)
    // private ReservationEntity reservation;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private SupplierEntity supplier;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BrandEntity brand;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RateEntity rate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private SizeEntity size;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "car_reservations",
        joinColumns = @JoinColumn(name = "car_entity_id"),
        inverseJoinColumns = @JoinColumn(name = "reservation_entity_id")
    )
    private List<ReservationEntity> reservations;
    
    
    public CarEntity(){}
    public CarEntity(
        LocationEntity location, 
        BrandEntity brand, 
        ModelEntity model, 
        ColorEntity color, 
        ReservationEntity reservation, 
        SupplierEntity supplier, 
        RateEntity rate,
        SizeEntity size) {
        this.location = location;
        this.model = model;
        this.color = color;
        this.supplier = supplier;
        this.rate = rate;
        this.brand = brand;
        this.size = size;
    };
    public CarEntity(
        LocationEntity location, 
        BrandEntity brand, 
        ModelEntity model, 
        ColorEntity color, 
        SupplierEntity supplier, 
        RateEntity rate,
        SizeEntity size) {
        this.location = location;
        this.model = model;
        this.color = color;
        this.supplier = supplier;
        this.rate = rate;
        this.brand = brand;
        this.size = size;
    };
    public CarEntity(
        LocationEntity location, 
        BrandEntity brand, 
        ModelEntity model, 
        ColorEntity color, 
        ReservationEntity reservation, 
        SupplierEntity supplier, 
        RateEntity rate) {
        this.location = location;
        this.model = model;
        this.color = color;
        this.supplier = supplier;
        this.rate = rate;
        this.brand = brand;
    };
    public CarEntity(
        LocationEntity location, 
        ModelEntity model, 
        ColorEntity color, 
        SupplierEntity supplier, 
        RateEntity rate) {
        this.location = location;
        this.model = model;
        this.color = color;
        this.supplier = supplier;
        this.rate = rate;
    };
    public CarEntity(
        LocationEntity location, 
        ModelEntity model, 
        SupplierEntity supplier, 
        RateEntity rate) {
        this.location = location;
        this.model = model;
        this.supplier = supplier;
        this.rate = rate;
    };
    public Long getId() {
        return id;
    }
    public LocationEntity getLocation() {
        return location;
    }
    public void setLocation(LocationEntity location) {
        this.location = location;
    }
    public ModelEntity getModel() {
        return model;
    }
    public void setModel(ModelEntity model) {
        this.model = model;
    }
    public ColorEntity getColor() {
        return color;
    }
    public void setColor(ColorEntity color) {
        this.color = color;
    }
    public List<ReservationEntity> getReservations() {
        return this.reservations;
    }
    public void setReservation(ReservationEntity reservation) {
        this.reservations.add(reservation);
    }
    public void removeReservation(ReservationEntity reservation) {
        this.reservations.remove(reservation);
    }
    public void removeLocation() {
        this.location = null;
    }
    public SupplierEntity getSupplier() {
        return supplier;
    }
    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }
    public RateEntity getRate() {
        return rate;
    }
    public void setRate(RateEntity rate) {
        this.rate = rate;
    }
    @Override
    public String toString() {
        String formatAsString = "CarEntity [id=" + id;
        if(reservations != null){
            formatAsString += ", reservations = " + reservations.toString();
        }
        formatAsString += ", location= " + location.toString() +  ", model=" + model.toString() + "rate=" + rate.toString() + "]";
        return formatAsString;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BrandEntity getBrand() {
        return brand;
    }
    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
    public SizeEntity getSize() {
        return size;
    }
    public void setSize(SizeEntity size) {
        this.size = size;
    }



}
