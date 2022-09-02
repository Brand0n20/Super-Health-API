package io.catalyte.training.sportsproducts.domains.shippingCost;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ShippingCostImpl.class)
public class ShippingCostImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  ShippingCost testShippingCost;
  ShippingCostRepository mockShippingCostRepository;
  @InjectMocks
  private ShippingCostImpl shippingCostServiceImpl;
  @Mock
  private ShippingCostRepository shippingCostRepository;

  @Test
  public void stubTest() {

  }

}
