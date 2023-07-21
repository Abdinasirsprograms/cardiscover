package com.abdi.cardiscover.controller.car;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

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
import com.abdi.cardiscover.requestbody.CarRequestBody;

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
