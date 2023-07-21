package com.abdi.cardiscover.entity;


import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Entity
public class SpecialtyClassEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToMany(cascade = CascadeType.ALL)
  private ArrayList<ModelEntity> models = new ArrayList<>();
  @ManyToMany(cascade = CascadeType.DETACH)
  private ArrayList<BrandEntity> brands = new ArrayList<>();
//   the default constructor exists only for the sake of JPA.
  protected SpecialtyClassEntity() {}

  public SpecialtyClassEntity(String name) {
    this.name = name;
  };
  public String getName(){
    return name;
  }

  @Override
  public String toString() {
    return String.format(
        "Class[id=%d, color=%s, model primary keys='%s', brand primary keys='%s']",
        id, name, models.toString(), brands.toString());
  }

  public Long getId() {
    return id;
  }

  public ArrayList<ModelEntity> getListOfModels() {
    return models;
  }
  public void setListOfModels(ArrayList<ModelEntity> models) {
    this.models = models;
  }
  public void setModelPk(ModelEntity model) {
    this.models.add(model);
  }

  public ArrayList<BrandEntity> getListOfBrands() {
    return brands;
  }
  public void setListOfBrands(ArrayList<BrandEntity> brands) {
    this.brands = brands;
  }
  public void setBrandPk(BrandEntity brand) {
    this.brands.add(brand);
  }
}
