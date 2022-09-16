package io.catalyte.training.sportsproducts.domains.promoCode;

import io.catalyte.training.sportsproducts.constants.promoCode.PromoCodeRepository;
import io.catalyte.training.sportsproducts.constants.promoCode.PromoCodeServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PromoCodeServiceImpl.class)
public class PromoCodeImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  @InjectMocks
  private PromoCodeServiceImpl promoCodeService;
  @Mock
  private PromoCodeRepository promoCodeRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

  }

  @Test
  public void stubTest() {

  }
}
