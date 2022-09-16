package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.Collection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Describes a purchase object that holds the information for a transaction
 */
@Entity
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "purchase")
  private Collection<LineItem> products;

  @Embedded
  private DeliveryAddress deliveryAddress;

  @Embedded
  private BillingAddress billingAddress;

  @Embedded
  private CreditCard creditCard;

  /**
   * The purchase date
   *
   * @author - Andrew Salerno
   */
  private String purchaseDate;

  /**
   * The users email
   *
   * @author - Andrew Salerno
   */
  private String userEmail;

  /**
   * Constructor for a new purchase
   */
  public Purchase() {
    this.billingAddress = new BillingAddress();
    this.deliveryAddress = new DeliveryAddress();
    this.creditCard = new CreditCard();
    this.purchaseDate = "";
    this.userEmail = "";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the date of a Purchase
   *
   * @return - The purchase date
   * @author - Andrew Salerno
   */
  public String getPurchaseDate() {
    return purchaseDate;
  }

  /**
   * Sets the date of a Purchase
   *
   * @param purchaseDate - The purchase date
   * @author - Andrew Salerno
   */
  public void setPurchaseDate(String purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  /**
   * Gets the user email linked to a Purchase
   *
   * @return - The users email address
   * @author - Andrew Salerno
   */
  public String getUserEmail() {
    return userEmail;
  }

  /**
   * Sets the user email of a Purchase
   *
   * @param userEmail - The users email address
   * @author - Andrew Salerno
   */
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public Collection<LineItem> getProducts() {
    return products;
  }

  public void setProducts(Collection<LineItem> products) {
    this.products = products;
  }

  public DeliveryAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    this.billingAddress = billingAddress;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  @Override
  public String toString() {
    return "Purchase{" +
        "id=" + id +
        ", deliveryAddress=" + deliveryAddress +
        ", billingAddress=" + billingAddress +
        ", creditCard=" + creditCard +
        '}';
  }
}