package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * This class provides tools for random generation of products.
 */
public class ProductFactory {

  private static final String[] colors = {"#000000", // white
      "#ffffff", // black
      "#39add1", // light blue
      "#3079ab", // dark blue
      "#c25975", // mauve
      "#e15258", // red
      "#f9845b", // orange
      "#838cc7", // lavender
      "#7d669e", // purple
      "#53bbb4", // aqua
      "#51b46d", // green
      "#e0ab18", // mustard
      "#637a91", // dark gray
      "#f092b0", // pink
      "#b7c0c7"  // light gray
  };
  private static final String[] demographics = {"Men", "Women", "Kids", "Non-Binary"};
  private static final String[] categories = {"Golf", "Soccer", "Basketball", "Hockey", "Football",
      "Running", "Baseball", "Skateboarding", "Boxing", "Weightlifting"};
  private static final String[] adjectives = {"Lightweight", "Slim", "Shock Absorbing", "Exotic",
      "Elastic", "Fashionable", "Trendy", "Next Gen", "Colorful", "Comfortable", "Water Resistant",
      "Wicking", "Heavy Duty"};
  private static final String[] types = {"Pant", "Short", "Shoe", "Glove", "Jacket", "Tank Top",
      "Sock", "Sunglasses", "Hat", "Helmet", "Belt", "Visor", "Shin Guard", "Elbow Pad", "Headband",
      "Wristband", "Hoodie", "Flip Flop", "Pool Noodle"};

  /**
   * Returns a random demographic from the list of demographics.
   *
   * @return - a demographic string
   */
  public static String getDemographic() {
    Random randomGenerator = new Random();
    return demographics[randomGenerator.nextInt(demographics.length)];
  }

  /**
   * Generates a random demographic.
   *
   * @return - a category string
   */

  public static String getCategory() {
    Random randomGenerator = new Random();
    return categories[randomGenerator.nextInt(categories.length)];
  }

  /**
   * Generates a random type.
   *
   * @return - a type string
   */

  public static String getType() {
    Random randomGenerator = new Random();
    return types[randomGenerator.nextInt(types.length)];
  }

  /**
   * Generates a random adjective.
   *
   * @return - an adjective string
   */

  public static String getAdjective() {
    Random randomGenerator = new Random();
    return adjectives[randomGenerator.nextInt(adjectives.length)];
  }

  /**
   * Generates a random color code.
   *
   * @return - a color code string
   */

  public static String getColorCode() {
    Random randomGenerator = new Random();
    return colors[randomGenerator.nextInt(colors.length)];
  }

  /**
   * Generates a random active state.
   *
   * @return - an active state boolean
   */
  public static Boolean getActive() {
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(2) > 0;
  }

  /**
   * Generates a random product offering id.
   *
   * @return - a product offering id
   */
  public static String getRandomProductId() {
    return "po-" + RandomStringUtils.random(7, false, true);
  }

  /**
   * Generates a random style code.
   *
   * @return - a style code string
   */
  public static String getStyleCode() {
    return "sc" + RandomStringUtils.random(5, false, true);
  }

  /**
   * Finds a random date between two date bounds.
   *
   * @param startInclusive - the beginning bound
   * @param endExclusive   - the ending bound
   * @return - a random date as a LocalDate
   */
  private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }

  /**
   * Generates a number of random products based on input.
   *
   * @param numberOfProducts - the number of random products to generate
   * @return - a list of random products
   */
  public List<Product> generateRandomProducts(Integer numberOfProducts) {

    List<Product> productList = new ArrayList<>();

    for (int i = 0; i < numberOfProducts; i++) {
      productList.add(createRandomProduct());
    }

    return productList;
  }

  /**
   * Uses random generators to build a product.
   *
   * @return - a randomly generated product
   */
  public Product createRandomProduct() {
    //Get the different types of randomly generated data needed
    Product product = new Product();
    String demographic = ProductFactory.getDemographic();
    String category = ProductFactory.getCategory();
    String primaryColorCode = ProductFactory.getColorCode();
    String secondaryColorCode = ProductFactory.getColorCode();
    String type = ProductFactory.getType();
    String adjective = ProductFactory.getAdjective();
    String description =
        "A " + adjective + " " + category + " that's great for " + demographic + "!";
    String name = adjective + " " + category + " " + type;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate date = ProductFactory.between(LocalDate.of(1970, 1, 1), LocalDate.now());
    String dateString = dateTimeFormatter.format(date);

    product.setPrimaryColorCode(primaryColorCode);
    product.setSecondaryColorCode(secondaryColorCode);
    product.setDescription(description);
    product.setName(name);
    product.setCategory(category);
    product.setDemographic(demographic);
    product.setGlobalProductCode(ProductFactory.getRandomProductId());
    product.setStyleNumber(ProductFactory.getStyleCode());
    product.setType(type);
    product.setActive(ProductFactory.getActive());
    product.setReleaseDate(dateString);

    return product;
  }

}
