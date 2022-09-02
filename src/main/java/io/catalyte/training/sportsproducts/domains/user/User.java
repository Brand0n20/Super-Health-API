package io.catalyte.training.sportsproducts.domains.user;

import io.catalyte.training.sportsproducts.domains.reviews.Review;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User entity in database
 */
@Entity
@Table(name = "users")
// this specifies what we want the table name to be, otherwise, it would just be named @Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String email;
  String role;
  String firstName;
  String lastName;
  String city;
  String street;
  String state;
  String zip;

  @OneToMany(mappedBy = "user")
  private List<Review> reviews;

  public User() {
  }

  public User(Long id, String email, String role, String firstName, String lastName, String street, String city, String state, String zip) {
    this.id = id;
    this.email = email;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  public User(String email, String role, String firstName, String lastName ,String street, String city, String state, String zip) {
    this.email = email;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }  public String getStreet () {
    return street;
  }

  public String getCity () {
    return city;
  }

  public String getState () {
    return state;
  }

  public String getZip () {
    return zip;
  }
  public void setStreet ( String street ) {
    this.street = street;
  }

  public void setCity ( String city ) {
    this.city = city;
  }

  public void setState ( String state ) {
    this.state = state;
  }

  public void setZip ( String zip ) {
    this.zip = zip;
  }



  @Override
  public String toString() {  // this just displays this stuff in the Java console
    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", role='" + role + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", zip='" + zip + '\'' +
        '}';
  }
}
