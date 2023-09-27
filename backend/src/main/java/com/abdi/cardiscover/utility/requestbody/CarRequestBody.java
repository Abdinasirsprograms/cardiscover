package com.abdi.cardiscover.utility.requestbody;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import org.springframework.format.annotation.DateTimeFormat;

// This class maps JSON POST objects to Java objects via the @RequestBody  
public class CarRequestBody {
    private Long id;
    private String location;
    private String brand;
    private String size;
    private String model;
    private String color;
    private String supplier;
    private BigDecimal rate;
    private String doLocation;
    private String puLocation;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private GregorianCalendar doDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private GregorianCalendar puDate;
    public CarRequestBody(String location, String brand, String size, String model, String color, String supplier,
            BigDecimal rate) {
        this.location = location;
        this.brand = brand;
        this.size = size;
        this.model = model;
        this.color = color;
        this.supplier = supplier;
        this.rate = rate;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public BigDecimal getRate() {
        return rate;
    }
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    public String getDoLocation() {
        return doLocation;
    }
    public void setDoLocation(String doLocation) {
        this.doLocation = doLocation;
    }
    public String getPuLocation() {
        return puLocation;
    }
    public void setPuLocation(String puLocation) {
        this.puLocation = puLocation;
    }
    public GregorianCalendar getDoDate() {
        return doDate;
    }
    public void setDoDate(GregorianCalendar doDate) {
        this.doDate = doDate;
    }
    public GregorianCalendar getPuDate() {
        return puDate;
    }
    public void setPuDate(GregorianCalendar puDate) {
        this.puDate = puDate;
    }
}
