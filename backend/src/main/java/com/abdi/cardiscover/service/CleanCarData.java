package com.abdi.cardiscover.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import com.abdi.cardiscover.entity.CarEntity;
import com.abdi.cardiscover.entity.ReservationEntity;

public class CleanCarData {
    public static HashMap<String, String> clean(CarEntity car){
        HashMap<String, String> results_cleaned = new HashMap<>();
        SimpleDateFormat converDateObjToString = new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss a zzz");
        if(car == null){return results_cleaned;}
        ReservationEntity availbilityEntity = car.getReservation();
        if(availbilityEntity != null){
            results_cleaned.put("reserved", "Car is booked");
            GregorianCalendar puTime = availbilityEntity.getPickupTime();
            GregorianCalendar doTime = availbilityEntity.getDropoffTime();
            if(puTime != null){
                results_cleaned.put("pickupTime", converDateObjToString.format(car.getReservation().getPickupTime().getTime()));
            } else {
                results_cleaned.put("pickupTime"," ");
            }
            if(doTime != null){
                results_cleaned.put("dropoffTime", converDateObjToString.format(car.getReservation().getDropoffTime().getTime()));
            } else {
                results_cleaned.put("dropoffTime"," ");
            }
        }
        results_cleaned.put("id", car.getId().toString());
        results_cleaned.put("location", car.getLocation().getName());
        results_cleaned.put("brand", car.getBrand().getName());
        results_cleaned.put("size", car.getSize().getName());
        results_cleaned.put("model", car.getModel().getName());
        results_cleaned.put("supplier", car.getSupplier().getName());
        results_cleaned.put("rate", car.getRate().getRate().toString());
        return results_cleaned;
    };

    public static List<HashMap<String,String>> cleanListOfCarData(List<CarEntity> cars){
        // Iterable<CarEntity> result = supplierRepository.findByName("Hertz").getCars();
        // List<CarEntity> cars = new ArrayList<>();
        List<HashMap<String,String>> results = new ArrayList<>();
        if(cars == null) return results;
        for(CarEntity car: cars){
            results.add(clean(car));
        }
        return results;
    };

    public static List<HashMap<String,String>> cleanCarDataIterable(Iterable<CarEntity> car){
        List<HashMap<String,String>> results = new ArrayList<>();
        for (CarEntity carEntity : car) {
            results.add(clean(carEntity));
        }
        return results;
    }
}
