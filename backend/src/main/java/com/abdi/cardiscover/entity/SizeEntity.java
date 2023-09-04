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
public class SizeEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToMany(cascade = CascadeType.PERSIST)
  private List<ModelEntity> models = new ArrayList<>();
  
  @OneToMany(cascade = CascadeType.PERSIST)
  private List<BrandEntity> brands = new ArrayList<>();
//   the default constructor exists only for the sake of JPA.
  protected SizeEntity() {}

  public SizeEntity(String name) {
    this.name = name;
  };
  public String getName(){
    return name;
  }

  @Override
  public String toString() {
    return String.format(
        "Size[id=%d, name=%s, model primary key='%s', car primary key='%s']",
        id, name, models, brands);
  }

  public Long getId() {
    return id;
  }

  public List<ModelEntity> getModels() {
    return models;
  }

  public void setModels(List<ModelEntity> models) {
    this.models = models;
  }
  public void setModel(ModelEntity model) {
    this.models.add(model);
  }

  public List<BrandEntity> getBrands() {
    return brands;
  }
  public void setBrands(List<BrandEntity> brands) {
    this.brands = brands;
  }
  public void setCar(BrandEntity brand) {
    this.brands.add(brand);
  }


}
