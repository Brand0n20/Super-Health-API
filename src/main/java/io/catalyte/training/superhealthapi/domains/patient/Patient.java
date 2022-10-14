package io.catalyte.training.superhealthapi.domains.patient;

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

  private int age;

  private int height;

  private int weight;

  private String insurance;

  private String gender;

  public Patient() {
  }

  public Patient(String firstName, String lastName, String ssn, String email,
      String street,
      String city, String state, String postal, int age, int height, int weight,
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

  public void setAge(int age) {
    this.age = age;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWeight(int weight) {
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

  public int getAge() {
    return age;
  }

  public int getHeight() {
    return height;
  }

  public int getWeight() {
    return weight;
  }

  public String getInsurance() {
    return insurance;
  }

  public String getGender() {
    return gender;
  }

}
