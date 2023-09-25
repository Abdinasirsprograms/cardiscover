package com.abdi.cardiscover.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class BrandEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToOne(cascade = CascadeType.ALL)
  private ImageEntity logo;
  @OneToMany(cascade = CascadeType.ALL)
  private List<ModelEntity> models = new ArrayList<>();  
  @ManyToMany(cascade = CascadeType.DETACH)
  private List<SpecialtyClassEntity> specialtyclasses = new ArrayList<>();  
  @OneToMany(cascade = CascadeType.DETACH)
  private List<CarEntity> cars;
  @ManyToOne(cascade = CascadeType.ALL)
  private SizeEntity sizes;
  //   the default constructor exists only for the sake of JPA.
  protected BrandEntity() {}

  public BrandEntity(String name) {
    this.name = name;
  };
  public String getName(){
    return name;
  }

  @Override
  public String toString() {
    return String.format(
        "Brand[id=%d, name=%s, models='%s', classes='%s', sizes='%s']",
        id, name, models.toString(), specialtyclasses.toString(), sizes.toString());
  }

  public Long getId() {
    return id;
  }

  public List<ModelEntity> getListOfModels() {
    return models;
  }
  public void setListOfModels(ArrayList<ModelEntity> models) {
    this.models = models;
  }

  public List<SpecialtyClassEntity> getListOfSpecialtyClasses() {
    return specialtyclasses;
  }
  public void setListOfSpecialtyClasses(ArrayList<SpecialtyClassEntity> specialtyclasses) {
    this.specialtyclasses = specialtyclasses;
  }
  public void addSpecialtyClass(SpecialtyClassEntity specialtyclass) {
    this.specialtyclasses.add(specialtyclass);
  }

  public ImageEntity getLogo() {
    return logo;
  }

  public void setLogo(ImageEntity logo) {
    this.logo = logo;
  }

  

}
