package com.abdi.cardiscover.controller.car;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdi.cardiscover.entity.car.CarEntity;
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
import com.abdi.cardiscover.requestbody.Car;
import com.abdi.cardiscover.utility.CleanCarData;

@Component 
@RestController
public class ModifyCar {
    private final CarRepository carRepository;
    private final ColorRepository colorRepository;
    private final LocationRepository locationRepository;
    private final ModelRepository modelRepository;
    private final AgencyRepository agencyRepository;
    private final ReservationRepository reservationRepository;
    private final BrandRepository brandRepository;
    private final RateRepository rateRepository;
    private final SizeRepository sizeRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    ModifyCar(
    CarRepository carRepository, 
    ColorRepository colorRepository,
    LocationRepository locationRepository,
    ModelRepository modelRepository,
    AgencyRepository agencyRepository, 
    ReservationRepository reservationRepository, 
    BrandRepository brandRepository, 
    RateRepository rateRepository, 
    SizeRepository sizeRepository, 
    SupplierRepository supplierRepository){
        this.carRepository = carRepository;
        this.colorRepository = colorRepository;
        this.locationRepository = locationRepository;
        this.modelRepository = modelRepository;
        this.agencyRepository = agencyRepository;
        this.reservationRepository = reservationRepository;
        this.brandRepository = brandRepository;
        this.rateRepository = rateRepository;
        this.sizeRepository = sizeRepository;
        this.supplierRepository = supplierRepository;
    }

    @PostMapping("/modify-car")
    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80"})
    @ResponseBody
    public HashMap<String,String> modifyCar(@RequestBody Car requestParams) throws SQLException {
        // Find by returns a ref to the db object, hence the get() here.
        CarEntity newCar = CreateOrGetCarObjects.run(
            requestParams,
            carRepository,
            colorRepository,
            locationRepository,
            modelRepository,
            agencyRepository,
            reservationRepository,
            brandRepository,
            rateRepository,
            sizeRepository,
            supplierRepository
        );
        return CleanCarData.clean(newCar);
    }
}
