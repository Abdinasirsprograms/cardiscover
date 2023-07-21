package com.abdi.cardiscover.entity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class RateEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private BigDecimal rate;
  @OneToMany(cascade = CascadeType.ALL)
  private List<CarEntity> cars = new ArrayList<>();
//   the default constructor exists only for the sake of JPA.
  public RateEntity() {}

  public RateEntity(BigDecimal rate) {
    this.rate = rate;
  };
  public BigDecimal getRate(){
    return rate;
  }

  @Override
  public String toString() {
    return String.format(
        "rate[id=%d, rate=%s, cars='%s']",
        id, rate.toString(), cars.toString());
  }

  public Long getId() {
    return id;
  }

  public List<CarEntity> getCars(){
    return cars;
  };

  public void setCars(ArrayList<CarEntity> cars) {
    this.cars = cars;
  }

  public void addCar(CarEntity car) {
    this.cars.add(car);
  }
}
