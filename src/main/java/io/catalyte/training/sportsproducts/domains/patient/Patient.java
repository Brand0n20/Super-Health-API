package io.catalyte.training.sportsproducts.domains.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is a representation of a patient
 */
@Entity
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  String firstName;

  String lastName;

  String ssn;

  String email;

  String street;

  String city;

  String state;

  String postal;

  double age;

  double height;

  double weight;

  String insurance;

  String gender;

  public Patient(long id, String firstName, String lastName, String ssn, String email,
      String street,
      String city, String state, String postal, double age, double height, double weight,
      String insurance, String gender) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.ssn = ssn;
    this.email = email;
    this.street = street;
    this.city = city;
    this.state = state;
    this.postal = postal;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.insurance = insurance;
    this.gender = gender;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setPostal(String postal) {
    this.postal = postal;
  }

  public void setAge(double age) {
    this.age = age;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public void setInsurance(String insurance) {
    this.insurance = insurance;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getSsn() {
    return ssn;
  }

  public String getEmail() {
    return email;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostal() {
    return postal;
  }

  public double getAge() {
    return age;
  }

  public double getHeight() {
    return height;
  }

  public double getWeight() {
    return weight;
  }

  public String getInsurance() {
    return insurance;
  }

  public String getGender() {
    return gender;
  }

}
