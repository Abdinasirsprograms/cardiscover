package com.abdi.cardiscover.controller.car;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdi.cardiscover.repository.CarRepository;

import com.abdi.cardiscover.repository.SupplierRepository;

@Component 
@RestController
public class DeleteCar {
    private final CarRepository carRepository;

    @Autowired
    DeleteCar(
    CarRepository carRepository, 
    SupplierRepository supplierRepository){
        this.carRepository = carRepository;
    }
        
    @GetMapping("/delete-all-cars")
    @CrossOrigin(origins = {"https://abdinasirnoor.com", "http://localhost:8080"})
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public void deleteAllCar() throws SQLException {
        carRepository.deleteAll();
    }
}
