package io.catalyte.training.superhealthapi.domains.reviews;

import static io.catalyte.training.superhealthapi.constants.Paths.REVIEWS_PATH;
import static io.catalyte.training.superhealthapi.domains.purchase.PurchaseApiTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewApiTest {

  @Autowired
  ReviewRepository reviewRepository;
  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getReviewsListForProductReturns200() throws Exception {
    mockMvc.perform(get(REVIEWS_PATH + "/product/3"))
        .andExpect(status().isOk());
  }

  @Test
  public void saveReviewReturn201StatusCode() throws Exception {
    String reviewTitle = "Review Test";
    String reviewComment = "This is a test for posting a review";
    long reviewRating = 4L;

    Review newReview = new Review(reviewTitle, reviewComment, reviewRating);
    mockMvc.perform(
            post(REVIEWS_PATH).contentType(MediaType.APPLICATION_JSON).content(
                    asJsonString(newReview))).andExpect(status().isCreated());
  }

}
