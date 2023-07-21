package com.abdi.cardiscover.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class SupplierEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToOne(cascade = CascadeType.ALL)
  private ImageEntity logo;
  @ManyToMany(cascade = CascadeType.ALL)
  private List<AgencyEntity> agencies;
  @OneToMany(cascade = CascadeType.ALL)
  private List<CarEntity> cars = new ArrayList<>();
//   the default constructor exists only for the sake of JPA.
  protected SupplierEntity() {}

  public SupplierEntity(String name) {
    this.name = name;
  };

  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format(
        "Model=[id=%d, model name='%s', agencies ='%s', color='%s']",
        id, name, agencies);
  };

  public Long getId() {
    return id;
  };

  
  public ImageEntity getLogo() {
    return logo;
  }

  public void setLogo(ImageEntity logo) {
    this.logo = logo;
  }


  public List<AgencyEntity> getAgencies(){
    return agencies;
  };

  public void setAgencies(List<AgencyEntity> agencies) {
    this.agencies = agencies;
  }

  public void setAgency(AgencyEntity agency) {
    this.agencies.add(agency);
  }

  public List<CarEntity> getCars(){
    return cars;
  };

  public void setCars(List<CarEntity> cars) {
    this.cars = cars;
  }

  public void addCar(CarEntity car) {
    this.cars.add(car);
  }
}
