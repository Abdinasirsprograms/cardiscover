package com.abdi.cardiscover.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdi.cardiscover.entity.CarEntity;
import com.abdi.cardiscover.entity.LocationEntity;
import com.abdi.cardiscover.entity.ReservationEntity;
import com.abdi.cardiscover.repository.CarRepository;
import com.abdi.cardiscover.repository.LocationRepository;
import com.abdi.cardiscover.repository.ReservationRepository;
import com.abdi.cardiscover.repository.SupplierRepository;
import com.abdi.cardiscover.requestbody.CarRequestBody;
import com.abdi.cardiscover.requestbody.ReservationRequestBody;
import com.abdi.cardiscover.service.CleanCarData;


@Component 
@RestController
public class Brand {
    // private final CarRepository carRepository;
    // private final LocationRepository locationRepository;
    // private final ReservationRepository reservationRepository;

    // @Autowired
    // GetCar(
    // CarRepository carRepository, 
    // LocationRepository locationRepository,
    // ReservationRepository reservationRepository, 
    // SupplierRepository supplierRepository){
    //     this.carRepository = carRepository;
    //     this.locationRepository = locationRepository;
    //     this. reservationRepository = reservationRepository;
    // }


    // // Get all avaliable cars based the location, and the reservation's pick up & drop off date and time
    // @PostMapping("/get-avaliable-cars")
    // 

    // @ResponseBody
    // // Serlizes the object as JSON due to the @ResponseBody annotation
    // public List<HashMap<String,String>> getCars(@RequestBody Reservation requestParams) throws SQLException {
    //     GregorianCalendar puTime = requestParams.getPuDate();
    //     String puLocation = requestParams.getPuLocation();
    //     GregorianCalendar doTime = requestParams.getDoDate();
    //     String doLocation = requestParams.getDoLocation();
    //     List<HashMap<String,String>> emptyResult = new ArrayList<>();
    //     ReservationEntity puTimeRecord = reservationRepository.findByPickupTime(puTime);
    //     ReservationEntity doTimeRecord = reservationRepository.findByPickupTime(doTime);
        
    //     // One-way trip if DO location and PU location are equal - DO location can also be blank
    //     if(puLocation.equalsIgnoreCase(doLocation) || doLocation.isEmpty()){
    //         LocationEntity location = locationRepository.findByName(puLocation);
    //         if(location == null) return emptyResult;
    //         List<CarEntity> cars = location.getCars();
    //         return CleanCarData.cleanListOfCarData(cars);
    //     } 

    //     List<HashMap<String,String>> doLocationResults = new ArrayList<>();
    //     List<HashMap<String,String>> puLocationResults = new ArrayList<>();
    //     LocationEntity puLocationEntity = locationRepository.findByName(puLocation);
    //     LocationEntity doLocationEntity = locationRepository.findByName(doLocation);
        
    //     if(doLocationEntity == null || puLocation == null ) return emptyResult;
        
    //     List<CarEntity> puLocationCars = puLocationEntity.getCars();
    //     List<CarEntity> doLocationCars = doLocationEntity.getCars();
    //     if(puLocationCars == null && doLocationCars == null){ return doLocationResults;}
    //     puLocationResults = CleanCarData.cleanListOfCarData(puLocationCars);
    //     doLocationResults = CleanCarData.cleanListOfCarData(doLocationCars);
    //     puLocationResults.addAll(doLocationResults);
        
    //     return puLocationResults;
    // }

    // /*
    //  * Get cars JUST based on location, allowing last-minute scheduling for cars with short reservations.
    //  * Extra time can be added to each reservation, this will allow suppliers to customize how long they may 
    //  * require to process a vehicle for a new customer after it's been returned. Then that way each reservation is always ready without manually setting the vehicle as 
    //  * "ready for pickup."
    //  */
    // @GetMapping("/get-car")
    // 

    // @ResponseBody
    // public HashMap<String,String> getCar(@RequestParam("id") Long id) throws SQLException {
    //     // Find by returns a ref to the db object, hence the get() here.
    //     CarEntity car = carRepository.findById(id).get();
    //     return CleanCarData.clean(car);
    // }


    
        
    // /*
    //  * Get all cars for bulk editing
    //  */
    // @GetMapping("/get-all-cars")
    // 

    // @ResponseBody
    // // Serlizes the object as JSON due to the @ResponseBody annotation
    // public List<HashMap<String,String>> getLocationData() throws SQLException {
    //     Iterable<CarEntity> result = carRepository.findAll();
    //     return CleanCarData.cleanCarDataIterable(result);
    // }

    
}
