package io.catalyte.training.sportsproducts.domains.PromoCode;

import io.catalyte.training.sportsproducts.domains.promoCode.PromoCode;
import io.catalyte.training.sportsproducts.domains.promoCode.PromoCodeRepository;
import io.catalyte.training.sportsproducts.domains.promoCode.PromoCodeServiceImpl;
import io.catalyte.training.sportsproducts.domains.purchase.PurchaseServiceImpl;
import java.util.List;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PromoCodeServiceImpl.class)
public class PromoCodeImplTest {
  @InjectMocks
  private PurchaseServiceImpl purchaseServiceImpl;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private PromoCodeRepository promoCodeRepository;





}
