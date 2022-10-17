package io.catalyte.training.superhealthapi.domains.Patient;

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
  private Long id;

  private String firstName;

  private String lastName;

  private String ssn;

  private String email;

  private String street;

  private String city;

  private String state;

  private String postal;

  private Integer age;

  private Integer height;

  private Integer weight;

  private String insurance;

  private String gender;

  public Patient() {
  }

  public Patient(String firstName, String lastName, String ssn, String email,
      String street,
      String city, String state, String postal, Integer age, Integer height, Integer weight,
      String insurance, String gender) {
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

  public void setId(Long id) {
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

  public void setAge(Integer age) {
    this.age = age;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public void setInsurance(String insurance) {
    this.insurance = insurance;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Long getId() {
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

  public Integer getAge() {
    return age;
  }

  public Integer getHeight() {
    return height;
  }

  public Integer getWeight() {
    return weight;
  }

  public String getInsurance() {
    return insurance;
  }

  public String getGender() {
    return gender;
  }

}
