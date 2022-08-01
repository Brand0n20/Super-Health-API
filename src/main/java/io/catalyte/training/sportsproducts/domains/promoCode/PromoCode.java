package io.catalyte.training.sportsproducts.domains.promoCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * promo_code entity in database
 */
@Entity // This annotation specifies that the class is an entity and is mapped to a database table
public class PromoCode  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String type;
  private String description;
  private String rate;


  public PromoCode(String description, String type, String rate, String title) {
    this.description = description;
    this.type = type;
    this.rate = rate;
    this.title = title;

  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }


  public void setTitle(String title) { this.title = title; }


  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getType() {
    return type;
  }

  public String getRate() {
    return rate;
  }

  public String getTitle() { return title; }


}

