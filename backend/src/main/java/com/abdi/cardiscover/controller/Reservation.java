package com.abdi.cardiscover.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.abdi.cardiscover.repository.CarRepository;

import com.abdi.cardiscover.requestbody.ReservationRequestBody;

@Component 
@RestController
public class Reservation {
    private final CarRepository carRepository;

    @Autowired
    Reservation(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    /*
     * Book car flow
     */
    @PostMapping("/book-car")
    @CrossOrigin(origins = {"http://192.168.1.196:4200", "http://localhost:8080"})
    @ResponseBody
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public HashMap<String,String> bookCar(@RequestBody ReservationRequestBody requestParams) throws SQLException {
        /* 
        * In the event the application starts without the DB.
        * Might be better to create a seperate script 
        * that handles just creating the DB.
        */
        Long reqstCarId = requestParams.getCarId();
        String reqstDoLocation = requestParams.getDoLocation();
        String reqstPuLocation = requestParams.getPuLocation();
        SimpleDateFormat converDateObjToString = new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss a zzz");
        GregorianCalendar reqstPuDate = requestParams.getPuDate();
        GregorianCalendar reqstDoDate = requestParams.getDoDate();
        
        HashMap<String, String> results_cleaned = new HashMap<>();
        return results_cleaned;
    }
    
}
