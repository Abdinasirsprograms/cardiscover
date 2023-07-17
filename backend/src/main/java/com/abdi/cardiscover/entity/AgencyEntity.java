package com.abdi.cardiscover.entity;


import java.math.BigDecimal;
import java.util.ArrayList;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;


@Entity
public class AgencyEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String tagline;
  private String name;
  private BigDecimal rate;
  @ManyToMany(cascade = CascadeType.ALL)
  private ArrayList<SupplierEntity> suppliers = new ArrayList<>();
  @OneToOne(cascade = CascadeType.ALL)
  private ImageEntity logo;
  

  public ImageEntity getLogo() {
    return logo;
  }

  public void setLogo(ImageEntity logo) {
    this.logo = logo;
  }

  public String getTagline() {
    return tagline;
  }

  public void setTagline(String tagline) {
    this.tagline = tagline;
  }

//   the default constructor exists only for the sake of JPA.
  protected AgencyEntity() {}

  public AgencyEntity(String name) {
    this.name = name;
  };
  public String getName() {
    return name;
  }
  public BigDecimal getRate(){
    return rate;
  }

  @Override
  public String toString() {
    return String.format(
        "Agency[id=%d, name='%s', suppliers='%s', logo='%s']",
        id, name, suppliers.toString());
  }

  public Long getId() {
    return id;
  }

  public ArrayList<SupplierEntity> getSuppliers(){
    return suppliers;
  };

  public void setSuppliers(ArrayList<SupplierEntity> suppliers) {
    this.suppliers = suppliers;
  }

  public void addCar(SupplierEntity car) {
    this.suppliers.add(car);
  }
}
