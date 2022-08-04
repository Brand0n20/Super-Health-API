package io.catalyte.training.sportsproducts.domains.promoCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * promo_code entity in database
 */
@Entity // This annotation specifies that the class is an entity and is mapped to a database table
public class PromoCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String type;
  private String description;
  private String rate;

  public PromoCode(String title, String type, String description, String rate) {
    this.title = title;
    this.type = type;
    this.description = description;
    this.rate = rate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }
}
