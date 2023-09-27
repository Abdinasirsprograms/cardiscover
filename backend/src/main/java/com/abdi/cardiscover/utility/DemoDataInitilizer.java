package com.abdi.cardiscover.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Component;

import com.abdi.cardiscover.controller.Car.CreateOrGetCarObjects;
import com.abdi.cardiscover.entity.BrandEntity;
import com.abdi.cardiscover.entity.CarEntity;
import com.abdi.cardiscover.entity.ColorEntity;
import com.abdi.cardiscover.entity.LocationEntity;
import com.abdi.cardiscover.entity.ModelEntity;
import com.abdi.cardiscover.entity.RateEntity;
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
import com.abdi.cardiscover.utility.requestbody.CarRequestBody;
import com.opencsv.CSVReader;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DemoDataInitilizer implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DemoDataInitilizer.class);
    private static CarRepository carRepository;
    private static ReservationRepository reservationRepository;
    private static LocationRepository locationRepository;
    private static ColorRepository colorRepository;
    private static ModelRepository modelRepository;
    private static BrandRepository brandRepository;
    private static AgencyRepository agencyRepository;
    private static SupplierRepository supplierRepository;
    private static SizeRepository sizeRepository;
    private static RateRepository rateRepository;

    @Autowired
    public DemoDataInitilizer(
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
        DemoDataInitilizer.carRepository = carRepository;
        DemoDataInitilizer.colorRepository = colorRepository;
        DemoDataInitilizer.locationRepository = locationRepository;
        DemoDataInitilizer.modelRepository = modelRepository;
        DemoDataInitilizer.agencyRepository = agencyRepository;
        DemoDataInitilizer.reservationRepository = reservationRepository;
        DemoDataInitilizer.brandRepository = brandRepository;
        DemoDataInitilizer.rateRepository = rateRepository;
        DemoDataInitilizer.sizeRepository = sizeRepository;
        DemoDataInitilizer.supplierRepository = supplierRepository;
    }

    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event){
        
        LOG.info("Begining data initialization...");
        initCars();

    }
    
    @Transactional
    private static void initCars() {
        LOG.info("Initializing cars...");
        // get car objects from csv file
        // and create or get them in the database for each csv line
        try {
            // use relative path to get demo data file
            String csvFilePath = "src/main/resources/demoData.csv";
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            // create csv reader
            CSVReader csvReader = new CSVReader(reader);
            
            // read one record at a time
            String[] record;
            LOG.info("Skipping the header row...");
            csvReader.readNext();
            LOG.info("Adding CSV Data...");
            LocationEntity location  = null;
            BrandEntity brand  = null;
            SizeEntity size  = null;
            ModelEntity model  = null;
            ColorEntity color  = null;
            SupplierEntity supplier  = null;
            RateEntity rate  = null;
            while ((record = csvReader.readNext()) != null) {
                String Location = record[0];
                String Brand = record[1];
                String Size = record[2];
                String Model = record[3];
                String Color = record[4];
                String Supplier = record[5];
                String rateString = record[6];
                BigDecimal Rate = new BigDecimal(rateString);
                try {
                    String reqLocation = Location;
                    LocationEntity getLocation = locationRepository.findByName(reqLocation);
                    if(getLocation != null){
                        location = getLocation;
                    }else {
                        location = new LocationEntity(reqLocation);
                        
                        locationRepository.save(location);
                    }
                    
                    // Create or get Brand Entity
                    String reqBrand = Brand;
                    BrandEntity getBrand = brandRepository.findByName(reqBrand);
                    if(getBrand != null){
                        brand = getBrand;
                    }else {
                        brand = new BrandEntity(reqBrand);
                        brandRepository.save(brand);
                    }
                    
                    // Create or get Size Entity
                    String reqSize = Size;
                    SizeEntity getSize = sizeRepository.findByName(reqSize);
                    if(getSize != null){
                        size = getSize;
                    }else {
                        size = new SizeEntity(reqSize);
                        sizeRepository.save(size);
                    }
        
                    // Create or get Model Entity
                    String reqModel = Model;
                    ModelEntity getModel = modelRepository.findByName(reqModel);
                    if(getModel != null){
                        model = getModel;
                    }else {
                        model = new ModelEntity(reqModel);
                        modelRepository.save(model);
                    }
                    
                    // Create or get Color Entity
                    String reqColor = Color;
                    ColorEntity getColor = colorRepository.findByColor(reqColor);
                    if(getColor != null){
                        color = getColor;
                    }else {
                        color = new ColorEntity(reqColor);
                        colorRepository.save(color);
                    }
                    
                    // Create or get Supplier Entity
                    String reqSupplier = Supplier;
                    SupplierEntity getSupplier = supplierRepository.findByName(reqSupplier);
                    if(getSupplier != null){
                        supplier = getSupplier;
                    }else {
                        supplier = new SupplierEntity(reqSupplier);
                        supplierRepository.save(supplier);
                    }
                    
                    // Create or get Rate Entity
                    BigDecimal reqRate = Rate; 
                    RateEntity getRate = rateRepository.findByRate(reqRate);
                    if(getRate != null){
                        rate = getRate;
                    }else {
                        rate = new RateEntity(reqRate.setScale(2, RoundingMode.DOWN));
                        rateRepository.save(rate);
                    }

                    CarEntity newCar = new CarEntity(location, brand, model, color, supplier, rate, size);
                    LOG.info("All Object",
                    location, brand, model, color, supplier, rate, size 
                    );

                    carRepository.save(newCar);
                    newCar.getLocation().addCar(newCar);
                } catch (Exception e) {
                    LOG.error("Error while saving car object: " + e.getMessage());
                }
            }
            // close readers
            csvReader.close();
            reader.close();
        }
         catch (Exception e) {
            LOG.error("Error while initializing cars: " + e.getMessage());
        }

    }
}


