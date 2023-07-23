package com.abdi.cardiscover.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private LocationEntity location;
    @ManyToOne(cascade = CascadeType.ALL)
    private ModelEntity model;
    @ManyToOne(cascade = CascadeType.ALL)
    private ColorEntity color;
    @ManyToOne(cascade = CascadeType.DETACH)
    private ReservationEntity reservation;
    @ManyToOne(cascade = CascadeType.ALL)
    private SupplierEntity supplier;
    @ManyToOne(cascade = CascadeType.ALL)
    private BrandEntity brand;
    @ManyToOne(cascade = CascadeType.ALL)
    private RateEntity rate;
    @ManyToOne(cascade = CascadeType.ALL)
    private SizeEntity size;
    @ManyToMany(cascade = CascadeType.ALL)
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
        this.reservation = reservation;
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
        this.reservation = reservation;
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
        return reservations;
    }
    public void setReservation(ReservationEntity reservation) {
        this.reservations.add(reservation);
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
        if(reservation != null){
            formatAsString += ", reservation = " + reservation.toString();
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
