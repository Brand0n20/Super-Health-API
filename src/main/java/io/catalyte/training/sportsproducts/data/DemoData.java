package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductRepository;
import io.catalyte.training.sportsproducts.domains.promoCode.PromoCodeRepository;
import io.catalyte.training.sportsproducts.domains.purchase.BillingAddress;
import io.catalyte.training.sportsproducts.domains.purchase.CreditCard;
import io.catalyte.training.sportsproducts.domains.purchase.Purchase;
import io.catalyte.training.sportsproducts.domains.purchase.PurchaseRepository;
import io.catalyte.training.sportsproducts.domains.reviews.Review;
import io.catalyte.training.sportsproducts.domains.reviews.ReviewRepository;
import io.catalyte.training.sportsproducts.domains.shippingCost.ShippingCost;
import io.catalyte.training.sportsproducts.domains.shippingCost.ShippingCostRepository;
import io.catalyte.training.sportsproducts.domains.user.User;
import io.catalyte.training.sportsproducts.domains.user.UserRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Because this class implements CommandLineRunner, the run method is executed as soon as the server
 * successfully starts and before it begins accepting requests from the outside. Here, we use this
 * as a place to run some code that generates and saves a list of random products into the
 * database.
 */
@Component
public class DemoData implements CommandLineRunner {

  public static final int DEFAULT_NUMBER_OF_PRODUCTS = 500;
  private final Logger logger = LogManager.getLogger(DemoData.class);
  ProductFactory productFactory = new ProductFactory();
  ShippingCostFactory shippingCostFactory = new ShippingCostFactory();
  ReviewFactory reviewFactory = new ReviewFactory();
  UserFactory userFactory = new UserFactory();
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private PurchaseRepository purchaseRepository;
  @Autowired
  private PromoCodeRepository promoCodeRepository;
  @Autowired
  private ReviewRepository reviewRepository;
  @Autowired
  private ShippingCostRepository shippingCostRepository;
  @Autowired
  private Environment env;

  @Override
  public void run(String... strings) {
    boolean loadData;

    try {
      // Retrieve the value of custom property in application.yml
      loadData = Boolean.parseBoolean(env.getProperty("products.load"));
    } catch (NumberFormatException nfe) {
      logger.error("config variable loadData could not be parsed, falling back to default");
      loadData = true;
    }

    if (loadData) {
      seedDatabase();
    }
  }

  private void seedDatabase() {
    int numberOfProducts;
    String[] usStates = {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas",
        "California", "Colorado", "Connecticut", "Delaware", "District of Columbia",
        "Federated States of Micronesia", "Florida", "Georgia", "Guam", "Hawaii", "Idaho",
        "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Marshall Islands",
        "Maryland",
        "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
        "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
        "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Palau",
        "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota",
        "Tennessee",
        "Texas", "Utah", "Vermont", "Virgin Island", "Virginia", "Washington", "West Virginia",
        "Wisconsin", "Wyoming"};

    try {
      // Retrieve the value of custom property in application.yml
      numberOfProducts = Integer.parseInt(env.getProperty("products.number"));
    } catch (NumberFormatException nfe) {
      logger.error("config variable numberOfProducts could not be parsed, falling back to default");
      // If it's not a string, set it to be a default value
      numberOfProducts = DEFAULT_NUMBER_OF_PRODUCTS;
    }

    // Generate products
    List<Product> productList = productFactory.generateRandomProducts(numberOfProducts);

    //Generate users
    List<User> userList = userFactory.generateUsers();

    //Generate reviews
    List<Review> reviewList = reviewFactory.generateRandomReviews(productList, userList);

    //Generate shipping costs
    List<ShippingCost> shippingCostList = shippingCostFactory.addStatesToList(usStates);

    // Persist them to the database
    logger.info("Loading " + numberOfProducts + " products...");
    productRepository.saveAll(productList);

    logger.info("Loading " + userList.size() + " users...");
    userRepository.saveAll(userList);

    logger.info("Loading " + reviewList.size() + " reviews...");
    reviewRepository.saveAll(reviewList);

    logger.info("Loading " + shippingCostList.size() + " shipping costs...");
    shippingCostRepository.saveAll(shippingCostList);

    // Persist them to the database
    logger.info("Data load completed. You can make requests now.");

    Purchase purchase1 = new Purchase();
    BillingAddress billingAddress = new BillingAddress();
    billingAddress.setEmail("bob@ross.com");
    purchase1.setBillingAddress(billingAddress);
    purchase1.setCreditCard(new CreditCard(
        "6543210987654321",
        "342",
        "12/26",
        "Bob Ross"));
    purchaseRepository.save(purchase1);

    Purchase purchase2 = new Purchase();
    purchase2.setBillingAddress(billingAddress);
    purchase2.setCreditCard(new CreditCard(
        "1234567890123456",
        "512",
        "05/23",
        "Bob Ross"
    ));
    purchaseRepository.save(purchase2);

    Purchase purchase3 = new Purchase();
    purchase3.setBillingAddress(billingAddress);
    purchase3.setCreditCard(new CreditCard(
        "4539379343750493",
        "472",
        "02/27",
        "Bob Ross"
    ));
    purchaseRepository.save(purchase3);

    Purchase purchase4 = new Purchase();
    billingAddress.setEmail("blah");

    purchase4.setBillingAddress(billingAddress);
    purchase4.setCreditCard(new CreditCard(
        "5527407157029653",
        "852",
        "11/23",
        "Amir Sharapov"

    ));

    purchaseRepository.save(purchase4);

  }

}
