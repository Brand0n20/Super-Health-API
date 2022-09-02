package io.catalyte.training.sportsproducts.domains.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class PurchaseServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  List<Purchase> testPurchase;
  @InjectMocks
  private PurchaseServiceImpl purchaseServiceImpl;
  @Mock
  private PurchaseRepository purchaseRepository;

  @Before
  public void setUp() {
//    MockitoAnnotations.initMocks(this);

    BillingAddress billingAddress = new BillingAddress();
    BillingAddress billingAddress2 = new BillingAddress();
    billingAddress.setEmail("bob@ross.com");
    billingAddress2.setEmail("blah");

    Purchase purchase1 = new Purchase();
    purchase1.setBillingAddress(billingAddress);

    Purchase purchase2 = new Purchase();
    purchase2.setBillingAddress(billingAddress);

    Purchase purchase3 = new Purchase();
    purchase3.setBillingAddress(billingAddress);

    Purchase purchase4 = new Purchase();
    purchase4.setBillingAddress(billingAddress2);

    testPurchase = Arrays.asList(purchase1, purchase2, purchase3, purchase4);

    when(purchaseRepository.findByBillingAddressEmail(anyString())).thenReturn(testPurchase);
  }

  /**
   * A list of purchases is returned when an email is provided
   */
  @Test
  public void getPurchasesByEmailReturnsPurchases() {
    List<Purchase> actual = purchaseServiceImpl.findPurchasesByEmail(anyString());
    assertEquals(testPurchase, actual);
  }
}