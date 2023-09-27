package com.abdi.cardiscover.entity;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class ModelEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToOne(cascade = CascadeType.ALL)
  private ImageEntity image;
  @ManyToOne(cascade = CascadeType.ALL)
  private BrandEntity brand;
  @ManyToOne(cascade = CascadeType.ALL)
  private SpecialtyClassEntity modelclass;
  @ManyToOne(cascade = CascadeType.ALL)
  private SizeEntity size;
  @ManyToOne(cascade = CascadeType.ALL)
  private ColorEntity color;
  @OneToMany(cascade = CascadeType.ALL)
  private List<CarEntity> cars = new ArrayList<>();
//   the default constructor exists only for the sake of JPA.
  protected ModelEntity() {}

  public ModelEntity(String name) {
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
        "Model=[id=%d, model name='%s', brand ='%s']",
        id, name, brand);
  };

  public Long getId() {
    return id;
  };

  
  public ImageEntity getLogo() {
    return image;
  }

  public void setLogo(ImageEntity image) {
    this.image = image;
  }

  public BrandEntity getBrand() {
    return brand;
  };

  public void setBrand(BrandEntity brand) {
    this.brand = brand;
  };

  public ColorEntity getColor() {
    return color;
  };

  public void setColor(ColorEntity color) {
    this.color = color;
  };


  public SizeEntity getSize() {
    return size;
  };

  public void setSize(SizeEntity size) {
    this.size = size;
  };

  public SpecialtyClassEntity getModelClass(){
    return modelclass;
  };

  public void setModelClass(SpecialtyClassEntity modelclass) {
    this.modelclass = modelclass;
  }
  
  public List<CarEntity> getModelCars(){
    return cars;
  };

  public void setModelCars(ArrayList<CarEntity> cars) {
    this.cars = cars;
  }

  public void setModelCar(CarEntity car) {
    this.cars.add(car);
  }
}
