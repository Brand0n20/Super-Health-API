package io.catalyte.training.sportsproducts.domains.reviews;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.user.User;
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
public class ReviewServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  List<Review> testReviews;
  @InjectMocks
  private ReviewServiceImpl reviewServiceImpl;
  @Mock
  private ReviewRepository reviewRepository;

  @Before
  public void setUp() {
//    MockitoAnnotations.initMocks(this);
    Product product1 = new Product();
    product1.setId(10L);

    User user1 = new User();
    user1.setFirstName("Kevin");

    Review review1 = new Review();
    Review review2 = new Review();

    review1.setProduct(product1);
    review1.setUser(user1);

    review2.setProduct(product1);
    review2.setUser(user1);

    testReviews = Arrays.asList(review1, review2);
    when(reviewRepository.findReviewsByProductId(anyLong())).thenReturn(testReviews);
  }

  // List of Reviews provided with product id;

  @Test
  public void getReviewsByProductIdReturnsListOfReviews() {
    List<Review> actual = reviewServiceImpl.getReviewsByProductId(10L);
    assertEquals(testReviews, actual);
  }

}
