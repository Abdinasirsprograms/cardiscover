package com.abdi.cardiscover.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdi.cardiscover.entity.CarEntity;
import com.abdi.cardiscover.entity.LocationEntity;
import com.abdi.cardiscover.entity.ReservationEntity;
import com.abdi.cardiscover.repository.AgencyRepository;
import com.abdi.cardiscover.repository.BrandRepository;
import com.abdi.cardiscover.repository.CarRepository;
import com.abdi.cardiscover.repository.ColorRepository;
import com.abdi.cardiscover.repository.LocationRepository;
import com.abdi.cardiscover.repository.ModelRepository;
import com.abdi.cardiscover.repository.RateRepository;
import com.abdi.cardiscover.repository.ReservationRepository;
import com.abdi.cardiscover.repository.SizeRepository;
import com.abdi.cardiscover.repository.SupplierRepository;
import com.abdi.cardiscover.requestbody.ReservationRequestBody;
import com.abdi.cardiscover.service.CleanCarData;

@Component 
@RestController
@RequestMapping("/location/")
public class Location {

    private final LocationRepository locationRepository;

    @Autowired
    Location(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }


    /* 
    * Go through all location's cars
    * split into 2 arrays - has res and no res
    * has organize res by dropofftime
    * while current pickup time < dropofftime
    * add car to list of results
    * add to cars with reservations date range
    * return both arrays
    */

    // Get all avaliable cars at the location
    @PostMapping("/get-avaliable-cars")
    @ResponseBody
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public List<HashMap<String,String>> getAvaliableCarsByLocation(@RequestBody ReservationRequestBody requestParams) throws SQLException {
        List<HashMap<String,String>> emptyResult = new ArrayList<>();
        // get pickup data
        String locationName = requestParams.getPuLocation();
        GregorianCalendar puTime = requestParams.getPuDate();
        String puLocation = requestParams.getPuLocation();
        GregorianCalendar doTime = requestParams.getDoDate();
        String doLocation = requestParams.getDoLocation();

        LocationEntity locationResult = locationRepository.findByName(locationName);
        if(locationResult == null) return emptyResult;
        List<CarEntity> AvaliableCarsWithinPickUpWindow = new ArrayList<>();
        List<CarEntity> AvaliableCarsAtLocation = locationResult.getCars();
        for (CarEntity currentCar : AvaliableCarsAtLocation) {
            List<ReservationEntity> carReservations = currentCar.getReservations();
            if(carReservations.size() >= 1){
                for (ReservationEntity reservation : carReservations) {
                    GregorianCalendar reservationDropOff = reservation.getDropoffTime();
                    // static GregorianCalendar puTimeInstance = puTime.getInstance();
                    if(reservationDropOff.before(puTime)){
                        AvaliableCarsWithinPickUpWindow.add(currentCar);
                        // reservationDropOff;
                    } else {
                        System.out.println("AHHHH THIS CAR IS BOOKED DURING YOUR PICKUP WINDOW");
                    }
                }
            } else {
                // no reservation
                AvaliableCarsWithinPickUpWindow.add(currentCar);
            }
        }
        return CleanCarData.cleanListOfCarData(AvaliableCarsWithinPickUpWindow);
    }
   
}
