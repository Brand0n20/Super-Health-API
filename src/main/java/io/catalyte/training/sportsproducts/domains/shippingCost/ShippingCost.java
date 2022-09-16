package io.catalyte.training.sportsproducts.domains.shippingCost;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * shipping cost entity in database
 */
@Entity
public class ShippingCost {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private String state;

  private Integer cost;

  private Double tax;

  public ShippingCost() {
  }

  public ShippingCost(String state, Integer cost, Double tax) {
    this.state = state;
    this.cost = cost;
    this.tax = tax;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public Double getTax() {
    return tax;
  }

  public void setTax(Double tax) {
    this.tax = tax;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShippingCost that = (ShippingCost) o;
    return Id.equals(that.Id) && state.equals(that.state) && cost.equals(that.cost) && tax.equals(
        that.tax);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Id, state, cost, tax);
  }

  @Override
  public String toString() {
    return "ShippingCost{" +
        "Id=" + Id +
        ", state='" + state + '\'' +
        ", cost=" + cost +
        ", tax=" + tax +
        '}';

  }
}
