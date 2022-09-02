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

  public ShippingCost() {
  }

  public ShippingCost(String state, Integer cost) {
    this.state = state;
    this.cost = cost;
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

  @Override
  public String toString() {
    return "ShippingCost{" +
        "Id=" + Id +
        ", state='" + state + '\'' +
        ", cost=" + cost +
        '}';
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
    return Objects.equals(Id, that.Id) && Objects.equals(state, that.state)
        && Objects.equals(cost, that.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Id, state, cost);
  }
}
