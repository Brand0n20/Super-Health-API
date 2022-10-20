package io.catalyte.training.superhealthapi.domains.Encounter;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Encounter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long patientId;

  private String notes;

  private String visitCode;

  private String provider;

  private String billingCode;

  private String icd10;

  private BigDecimal totalCost;

  private BigDecimal copay;

  private String chiefComplaint;

  private Float pulse;

  private Float systolic;

  private Float diastolic;

  private String date;

  public Encounter() {
  }

  public Encounter(Long patientId, String notes, String visitCode, String provider,
      String billingCode, String icd10, BigDecimal totalCost, BigDecimal copay,
      String chiefComplaint,
      Float pulse, Float systolic, Float diastolic, String date) {
    this.patientId = patientId;
    this.notes = notes;
    this.visitCode = visitCode;
    this.provider = provider;
    this.billingCode = billingCode;
    this.icd10 = icd10;
    this.totalCost = totalCost;
    this.copay = copay;
    this.chiefComplaint = chiefComplaint;
    this.pulse = pulse;
    this.systolic = systolic;
    this.diastolic = diastolic;
    this.date = date;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public void setVisitCode(String visitCode) {
    this.visitCode = visitCode;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public void setBillingCode(String billingCode) {
    this.billingCode = billingCode;
  }

  public void setIcd10(String icd10) {
    this.icd10 = icd10;
  }

  public void setTotalCost(BigDecimal totalCost) {
    this.totalCost = totalCost;
  }

  public void setCopay(BigDecimal copay) {
    this.copay = copay;
  }

  public void setChiefComplaint(String chiefComplaint) {
    this.chiefComplaint = chiefComplaint;
  }

  public void setPulse(Float pulse) {
    this.pulse = pulse;
  }

  public void setSystolic(Float systolic) {
    this.systolic = systolic;
  }

  public void setDiastolic(Float diastolic) {
    this.diastolic = diastolic;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public Long getPatientId() {
    return patientId;
  }

  public String getNotes() {
    return notes;
  }

  public String getVisitCode() {
    return visitCode;
  }

  public String getProvider() {
    return provider;
  }

  public String getBillingCode() {
    return billingCode;
  }

  public String getIcd10() {
    return icd10;
  }

  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public BigDecimal getCopay() {
    return copay;
  }

  public String getChiefComplaint() {
    return chiefComplaint;
  }

  public Float getPulse() {
    return pulse;
  }

  public Float getSystolic() {
    return systolic;
  }

  public Float getDiastolic() {
    return diastolic;
  }

  public String getDate() {
    return date;
  }
}
