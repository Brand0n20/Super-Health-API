package io.catalyte.training.superhealthapi.domains.purchase;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests the methods of a purchase repository using real methods for coverage
 *
 * @author - Andrew Salerno
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseRepositoryImplTest {

  /**
   * Use real methods and inject the mocks starting at PurchaseRepositoryImpl
   *
   * @author - Andrew Salerno
   */
  @InjectMocks
  private PurchaseRepositoryImpl purchaseRepositoryImpl = Mockito.mock(PurchaseRepositoryImpl.class,
      Mockito.CALLS_REAL_METHODS);

  /**
   * Use real methods and Mock the purchaseRepository
   *
   * @author - Andrew Salerno
   */
  @Mock
  private PurchaseRepository purchaseRepository = Mockito.mock(PurchaseRepository.class,
      Mockito.CALLS_REAL_METHODS);


  /**
   * Uses real methods to test the purchaseRepositoryImpl
   *
   * @author - Andrew Salerno
   */
  @Test
  public void findByBillingAddressEmailReturnsPurchase() {
    List<Purchase> expected = new ArrayList<>();
    List<Purchase> actual = purchaseRepositoryImpl.findByBillingAddressEmail(
        "asalerno@catalyte.io");
    assertEquals(expected, actual);
  }

  /**
   * Uses real methods to test the purchaseRepositoryImpl
   *
   * @author - Andrew Salerno
   */
  @Test
  public void findByUserEmailReturnsPurchase() {
    List<Purchase> expected = new ArrayList<>();
    List<Purchase> actual = purchaseRepositoryImpl.findByUserEmail("asalerno@catalyte.io");
    assertEquals(expected, actual);
  }
}