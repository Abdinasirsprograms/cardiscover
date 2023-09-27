package com.abdi.cardiscover.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdi.cardiscover.entity.BrandEntity;
import com.abdi.cardiscover.entity.CarEntity;
import com.abdi.cardiscover.entity.ColorEntity;
import com.abdi.cardiscover.entity.LocationEntity;
import com.abdi.cardiscover.entity.ModelEntity;
import com.abdi.cardiscover.entity.RateEntity;
import com.abdi.cardiscover.entity.ReservationEntity;
import com.abdi.cardiscover.entity.SizeEntity;
import com.abdi.cardiscover.entity.SupplierEntity;
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
import com.abdi.cardiscover.service.CleanCarData;
import com.abdi.cardiscover.utility.requestbody.CarRequestBody;
import com.abdi.cardiscover.utility.requestbody.ReservationRequestBody;

@Component 
@RestController
@RequestMapping("/car/")
public class Car {
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
    Car(
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


    /*
     * Create new cars
     */
    @PostMapping("/create-car")
    @ResponseBody
    public HashMap<String, String>  createCar(@RequestBody CarRequestBody requestParams) throws SQLException {
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

        // if(newCar.getReservations() != null){
        //     if(newCar.getReservations().getCars().size() < 1){
        //         List<CarEntity> oneCarList = new ArrayList<>();
        //         oneCarList.add(newCar);
        //         newCar.getReservations().setCar(oneCarList);
        //     }
        // }

        newCar.getLocation().addCar(newCar);
        CarEntity savedCar = carRepository.save(newCar);
        return CleanCarData.clean(savedCar);

        };

    /*
     * Get cars JUST based on location, allowing last-minute scheduling for cars with short reservations.
     * Extra time can be added to each reservation, this will allow suppliers to customize how long they may 
     * require to process a vehicle for a new customer after it's been returned. Then that way each reservation is always ready without manually setting the vehicle as 
     * "ready for pickup."
     */
    @GetMapping("/get-car")
    @ResponseBody
    public HashMap<String,String> getCar(@RequestParam("id") Long id) throws SQLException {
        // Find by returns a ref to the db object, hence the get() here.
        CarEntity car = carRepository.findById(id).get();
        return CleanCarData.clean(car);
    }

    @GetMapping("/delete-car/{id}")
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public void deleteCar(@PathVariable Long id) throws SQLException {
        Optional<CarEntity> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent() != true){
            return;
        }
        CarEntity car = optionalCar.get();
        // Decided to use the car repo as the place to delete reservation entities
        List<ReservationEntity> reservations = car.getReservations();
        List<Long> reservationIds = new ArrayList<>();
        for (ReservationEntity reservationEntity : reservations) {
            reservationIds.add(reservationEntity.getId());
            // car.removeReservation(reservationEntity);
            // car.getLocation().removeCar(car);
            // car.removeLocation();
            // locationRepository.save(car.getLocation());
            //     System.out.println("\n\n\nREMOVING RESERVATION ENTITY FROM CAR\n\n\n\n\n\n");
            // car.removeReservation(reservationEntity);
            //     // carRepository.save(car);    
            //     System.out.println("\n\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n\n\n\n\n");
        }
        car.getLocation().removeCar(car);
        carRepository.save(car);
        for (Long reservationId : reservationIds) {
            System.out.println("\n\n\n*******DELETING RESERVATION******\n\n\n\n\n\n");
            reservationRepository.deleteById(reservationId);
            System.out.println("\n\n\n*************\n\n\n\n\n\n");
        }
        // for (ReservationEntity reservation: reservations) {
        // }
        carRepository.delete(car);
    }
    
        
    /*
     * Get all cars for bulk editing
     */
    @GetMapping("/get-all-cars")
    @ResponseBody
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public List<HashMap<String,String>> getLocationData() throws SQLException {
        Iterable<CarEntity> result = carRepository.findAll();
        return CleanCarData.cleanCarDataIterable(result);
    }


    
    @PostMapping("/modify-car")
    @ResponseBody
    public HashMap<String,String> modifyCar(@RequestBody CarRequestBody requestParams) throws SQLException {
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

        
    @GetMapping("/delete-all-cars")
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public void deleteAllCar() throws SQLException {
        carRepository.deleteAll();
    }


    @Component 
    public class CreateOrGetCarObjects {
        public static CarEntity run(CarRequestBody requestParams,
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
            LocationEntity location  = null;
            BrandEntity brand  = null;
            SizeEntity size  = null;
            ModelEntity model  = null;
            ColorEntity color  = null;
            SupplierEntity supplier  = null;
            RateEntity rate  = null;
            ReservationEntity reservation  = null;
            // AvailabilityEntity foundAvailbilityPickupTime = reservationRepository.findByPickupTime(pickupTime);
            // AvailabilityEntity foundAvailbilityDropoffTime = reservationRepository.findByDropoffTime(dropoffTime);
            // if(foundAvailbilityDropoffTime != null){
            //     reservation = foundAvailbilityDropoffTime;
            //     reservationRepository.save(reservation);
            // }
            // else if(foundAvailbilityPickupTime != null){
            //     reservation = foundAvailbilityPickupTime;
            //     reservationRepository.save(reservation);
            // } else {
            //     reservationRepository.save(reservation);
            // }
            // newCar.setAvailability(reservation);
            // Create or get Location Entity
            String reqLocation = requestParams.getLocation();
            LocationEntity getLocation = locationRepository.findByName(reqLocation);
            if(getLocation != null){
                location = getLocation;
            }else {
                location = new LocationEntity(reqLocation);
                locationRepository.save(location);
            }
            
            // Create or get Brand Entity
            String reqBrand = requestParams.getBrand();
            BrandEntity getBrand = brandRepository.findByName(reqBrand);
            if(getBrand != null){
                brand = getBrand;
            }else {
                brand = new BrandEntity(reqBrand);
                brandRepository.save(brand);
            }
            
            // Create or get Size Entity
            String reqSize = requestParams.getSize();
            SizeEntity getSize = sizeRepository.findByName(reqSize);
            if(getSize != null){
                size = getSize;
            }else {
                size = new SizeEntity(reqSize);
                sizeRepository.save(size);
            }

            // Create or get Model Entity
            String reqModel = requestParams.getModel();
            ModelEntity getModel = modelRepository.findByName(reqModel);
            if(getModel != null){
                model = getModel;
            }else {
                model = new ModelEntity(reqModel);
                modelRepository.save(model);
            }
            
            // Create or get Color Entity
            String reqColor = requestParams.getColor();
            ColorEntity getColor = colorRepository.findByColor(reqColor);
            if(getColor != null){
                color = getColor;
            }else {
                color = new ColorEntity(reqColor);
                colorRepository.save(color);
            }
            
            // Create or get Supplier Entity
            String reqSupplier = requestParams.getSupplier();
            SupplierEntity getSupplier = supplierRepository.findByName(reqSupplier);
            if(getSupplier != null){
                supplier = getSupplier;
            }else {
                supplier = new SupplierEntity(reqSupplier);
                supplierRepository.save(supplier);
            }
            
            // Create or get Rate Entity
            BigDecimal reqRate = requestParams.getRate(); 
            RateEntity getRate = rateRepository.findByRate(reqRate);
            if(getRate != null){
                rate = getRate;
            }else {
                rate = new RateEntity(reqRate.setScale(2, RoundingMode.DOWN));
                rateRepository.save(rate);
            }

            // Create or get Car Entity
            Long reqCarId = requestParams.getId();
            CarEntity getCar = null;
            if(reqCarId != 0){
                getCar = carRepository.findById(reqCarId).get();
            }
            if(getCar != null){
                getCar.setBrand(brand);
                getCar.setLocation(location);
                getCar.setModel(model);
                getCar.setColor(color);
                getCar.setSupplier(supplier);
                getCar.setRate(rate);
                getCar.setSize(size);
                carRepository.save(getCar);
                return  getCar;
            }
            return new CarEntity(location, brand, model, color, supplier, rate, size);
        }
    }
}

