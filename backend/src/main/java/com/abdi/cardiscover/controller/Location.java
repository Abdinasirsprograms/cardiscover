package com.abdi.cardiscover.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdi.cardiscover.entity.CarEntity;
import com.abdi.cardiscover.entity.LocationEntity;


import com.abdi.cardiscover.repository.CarRepository;
import com.abdi.cardiscover.repository.LocationRepository;

import com.abdi.cardiscover.requestbody.ReservationRequestBody;
import com.abdi.cardiscover.service.CleanCarData;

@Component 
@RestController
@RequestMapping("/location/")
public class Location {

    private final LocationRepository locationRepository;
    private final DataSource dataSource;
    private final CarRepository carRepository;

    @Autowired
    Location(LocationRepository locationRepository, DataSource dataSource, CarRepository carRepository){
        this.locationRepository = locationRepository;
        this.dataSource = dataSource;
        this.carRepository = carRepository;
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
        GregorianCalendar doTime = requestParams.getDoDate();

        LocationEntity locationResult = locationRepository.findByName(locationName);
        if(locationResult == null) return emptyResult;
        List<CarEntity> AvaliableCarsAtLocation = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql ="SELECT DISTINCT carTable.* " +
                        "FROM car_entity carTable " +
                        "LEFT JOIN car_entity_reservations carReservationTable ON carTable.id = carReservationTable.car_entity_id " +
                        "LEFT JOIN reservation_entity reservationTable ON carReservationTable.reservations_id = reservationTable.id " +
                        "WHERE carTable.location_id = ? " +
                        "AND (reservationTable.id IS NULL OR reservationTable.dropoff_time < ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, locationResult.getId());
            // Not sure I need to check for the requested dropoff time....
            // statement.setTimestamp(2, new Timestamp(doTime.getTimeInMillis()));
            statement.setTimestamp(2, new Timestamp(puTime.getTimeInMillis()));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Process each row of the result set
                Long id = resultSet.getLong("id");
                Optional<CarEntity> findCar = carRepository.findById(id);
                if(findCar == null) continue;
                CarEntity currentCar = findCar.get(); 
                AvaliableCarsAtLocation.add(currentCar);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return CleanCarData.cleanListOfCarData(AvaliableCarsAtLocation);
    }
   
}
