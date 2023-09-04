package com.abdi.cardiscover.entity;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class ColorEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String color;
  @OneToMany(cascade = CascadeType.ALL)
  private List<ModelEntity> models = new ArrayList<>();
  
  @OneToMany(cascade = CascadeType.PERSIST)
  private List<CarEntity> car = new ArrayList<>();
//   the default constructor exists only for the sake of JPA.
  protected ColorEntity() {}

  public ColorEntity(String color) {
    this.color = color;
  };
  public String getName(){
    return color;
  }

  @Override
  public String toString() {
    return String.format(
        "Color[id=%d, color=%s, model primary key='%s', car primary key='%s']",
        id, color, models, car);
  }

  public Long getId() {
    return id;
  }

  public List<ModelEntity> getModels() {
    return models;
  }

  public void setModels(ArrayList<ModelEntity> models) {
    this.models = models;
  }
  public void setModel(ModelEntity model) {
    this.models.add(model);
  }

  public List<CarEntity> getCars() {
    return car;
  }
  public void setCars(ArrayList<CarEntity> car) {
    this.car = car;
  }
  public void setCar(CarEntity car) {
    this.car.add(car);
  }


}
