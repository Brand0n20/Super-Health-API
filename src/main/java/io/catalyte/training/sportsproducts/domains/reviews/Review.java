package io.catalyte.training.sportsproducts.domains.reviews;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.user.User;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Review Entity in the database
 */
@Entity
@Table(name = "reviews")
// @JsonIgnoreProperties({"product"})
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne
  @Embedded
  User user;

  @ManyToOne
  @Embedded
  Product product;

  @Size(max = 50)
  String title;

  @Size(max = 2000)
  String comment;

  double rating;

  String postedDate;

  public Review() {
  }

  public Review(String title, String comment, double rating) {
    this.title = title;
    this.comment = comment;
    this.rating = rating;
    this.user = getUser();
    this.product = getProduct();
  }

  public String getPostedDate() {
    return postedDate;
  }

  public void setPostedDate(String postedDate) {
    this.postedDate = postedDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        ", user=" + user +
        ", product=" + product +
        ", title='" + title + '\'' +
        ", comment='" + comment + '\'' +
        ", rating=" + rating +
        ", postedDate='" + postedDate + '\'' +
        '}';
  }
}