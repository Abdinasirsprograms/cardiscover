package com.abdi.cardiscover.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
import com.abdi.cardiscover.entity.ReservationEntity;
import com.abdi.cardiscover.repository.CarRepository;
import com.abdi.cardiscover.repository.ReservationRepository;
import com.abdi.cardiscover.requestbody.ReservationRequestBody;
import com.abdi.cardiscover.service.CleanCarData;

@Component 
@RestController
@RequestMapping("/reservation/")
public class Reservation {
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;    
    @Autowired
    Reservation(CarRepository carRepository, ReservationRepository reservationRepository){
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    /*
     * Book car flow
     */
    @PostMapping("/book-car")
    @ResponseBody
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public HashMap<String,String> bookCar(@RequestBody ReservationRequestBody requestParams) throws SQLException {
        /* 
        * In the event the application starts without the DB.
        * Might be better to create a seperate script 
        * that handles just creating the DB.
        */
        Long reqstCarId = requestParams.getId();
        String reqstDoLocation = requestParams.getDoLocation();
        String reqstPuLocation = requestParams.getPuLocation();
        SimpleDateFormat converDateObjToString = new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss a zzz");
        GregorianCalendar reqstPuDate = requestParams.getPuDate();
        GregorianCalendar reqstDoDate = requestParams.getDoDate();
        Optional<CarEntity> findRequestedCar = this.carRepository.findById(reqstCarId);
        HashMap<String, String> results_cleaned = new HashMap<>();
        if(findRequestedCar.isPresent() == false) return results_cleaned;
        CarEntity requestedCar = findRequestedCar.get();
        if(requestedCar == null) return results_cleaned;

        ReservationEntity reservation = new ReservationEntity(reqstPuDate, reqstDoDate);
        reservation.setCar(requestedCar);
        requestedCar.setReservation(reservation);
        this.reservationRepository.save(reservation);
        this.carRepository.save(requestedCar);
        return CleanCarData.clean(requestedCar);
    }
    
    @GetMapping("/get-all-bookings")
    @ResponseBody
    public List<HashMap<String,String>> getAllReservations() throws SQLException {
        List<HashMap<String, String>> results_cleaned = new ArrayList<>();
        List<ReservationEntity> allReservations = this.reservationRepository.findAll();
        for (ReservationEntity reservation : allReservations) {
            List<CarEntity> allCarsWithReservations = reservation.getCars();
            for (CarEntity car : allCarsWithReservations) {
                results_cleaned.add(CleanCarData.clean(car));
            }
        }
        return results_cleaned;
    }
        
    @GetMapping("/delete-all-bookings")
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public void deleteAllBookings() throws SQLException {
        reservationRepository.deleteAll();
    }

    @GetMapping("/delete-booking/{id}")
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public void deleteBooking(@PathVariable Long id) throws SQLException {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent() == true){
            ReservationEntity reservation = optionalReservation.get();
            List<CarEntity> carReservations = reservation.getCars();
            for (CarEntity car : carReservations) {
                reservation.getCars().remove(car);
                car.removeReservation(reservation);
                carRepository.save(car);
                reservationRepository.delete(reservation);
            }
        }
    }


    @GetMapping("/get-booking/{id}")
    @ResponseBody
    public HashMap<String,String> getReservation(@PathVariable Long id) throws SQLException {
        HashMap<String, String> results_cleaned = new HashMap<>();
        Optional<ReservationEntity> findReservation = reservationRepository.findById(id);
        if(findReservation != null) {
            ReservationEntity reservation = findReservation.get();
            results_cleaned = CleanCarData.clean(reservation.getCar());
        }
        return results_cleaned;
    }
}
