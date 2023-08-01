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
        if(car.getReservations() != null){
            List<ReservationEntity> reservations = car.getReservations();
            if(reservations.size() >= 1){
                results_cleaned.put("reserved", "Car is booked");
                GregorianCalendar puTime = reservations.get(0).getPickupTime();
                GregorianCalendar doTime = reservations.get(0).getDropoffTime();
                if(puTime != null){
                    results_cleaned.put("pickupTime", converDateObjToString.format(car.getReservations().get(0).getPickupTime().getTime()));
                }
                if(doTime != null){
                    results_cleaned.put("dropoffTime", converDateObjToString.format(car.getReservations().get(0).getDropoffTime().getTime()));
                } 
            }
        }
        // Manual checks for all the objects 
        String carId = "";
        String carLocation = "";
        String carBrand = "";
        String carSize = "";
        String carModel = "";
        String carSupplier = "";
        String carRate = "";
        
        if(car.getId() != null){
            carId = car.getId().toString();
        }

        if(car.getLocation() != null){
            carLocation = car.getLocation().getName();
        }
        
        if(car.getBrand() != null){
            carBrand = car.getBrand().getName();
        }
        
        if(car.getSize() != null){
            carSize = car.getSize().getName();
        }

        if(car.getModel() != null){
            carModel = car.getModel().getName();
        }
        if(car.getSupplier() != null){
            carSupplier = car.getSupplier().getName();
        }
        if(car.getRate() != null){
            carRate = car.getRate().getRate().toString();
        }


        results_cleaned.put("id", carId);
        results_cleaned.put("location", carLocation);
        results_cleaned.put("brand", carBrand);
        results_cleaned.put("size", carSize);
        results_cleaned.put("model",  carModel);
        results_cleaned.put("supplier",carSupplier);
        results_cleaned.put("rate", carRate);
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
        if(car == null) return results;
        for (CarEntity carEntity : car) {
            results.add(clean(carEntity));
        }
        return results;
    }
}
