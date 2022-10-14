package io.catalyte.training.superhealthapi.data;

import io.catalyte.training.superhealthapi.domains.product.Product;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * This class provides tools for random generation of products.
 */
public class ProductFactory {

  private static final String[] colors = {"#000000", // black
      "#ffffff", // white
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

  private static final String[] brands = {"Nike", "Adidas", "Reebok", "New Balance", "Sketchers",
      "Old Navy", "Under Armour", "Puma", "The North Face", "Columbia", "Champion",
      "Fruit Of The Loom"};

  private static final String[] materials = {"Cotton", "Wool", "Silk", "Leather", "Velvet", "Satin",
      "Denim", "Tungsten", "Hand Blown Glass", "Bamboo", "Granite", "Vinyl"};

  private static final Map<String, String> images = Map.ofEntries(
      Map.entry("Pant",
          "https://www.freshidealiving.com.au/assets/full/HL98239_BWS_L.jpg?20210226194903"),
      Map.entry("Short",
          "https://i5.walmartimages.com/asr/4fd5cb51-ecbc-456e-b80d-821f9709b4f5_1.446a735a15f656b08601108abf0c3ada.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF"),
      Map.entry("Glove", "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRQAoeay12gBdOsikc5A6Qff_24LvKR4YA2YQEcU-5JmqxqHRIDbpUPfS-Da7xLbRSsNTe507kWSA&usqp=CAc"),
      Map.entry("Shoe",
          "https://assets.vogue.com/photos/6216806693122fd329921153/master/w_1280%2Cc_limit/slide_21.jpg"),
      Map.entry("Jacket", "https://m.media-amazon.com/images/I/51G1w0S6HjL._AC_UX569_.jpg"),
      Map.entry("Tank Top",
          "https://www.brooksrunning.com/dw/image/v2/BGPF_PRD/on/demandware.static/-/Sites-brooks-master-catalog/default/dw9c793ac3/original/221545/221545-510-lf-distance-tank-womens-sleeveless-running-top.png?sw=868&sh=868&sm=fit"),
      Map.entry("Sock",
          "https://cdn.shopify.com/s/files/1/0234/4461/products/i-love-cheese-socks_1200x.jpg?v=1653166789"),
      Map.entry("Sunglasses",
          "https://assets.oakley.com/is/image/OakleyEYE/888392266811__STD__shad__qt.png?impolicy=OO_ratio&width=2000"),
      Map.entry("Hat",
          "https://cdn.vox-cdn.com/thumbor/lhc1uc903lsSPwYDJAUT51oIHbU=/0x0:2048x1638/920x613/filters:focal(852x711:1178x1037):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/66585997/1__59_.0.png"),
      Map.entry("Helmet",
          "https://cdn.shopify.com/s/files/1/0685/9327/products/helmet-paint_grande.jpg?v=1648583141"),
      Map.entry("Belt",
          "https://scheels.scene7.com/is/image/Scheels/88779137601?wid=400&hei=400&qlt=50"),
      Map.entry("Visor", "https://static.augustasportswear.com/product/6227_045_aws_640.jpg"),
      Map.entry("Shin Guard",
          "https://images.dickssportinggoods.com/is/image/GolfGalaxy/17NIKAJGRDBLCKXXXSCS_White?qlt=70&wid=1100&fmt=webp"),
      Map.entry("Elbow Pad",
          "https://www.sweetprotection.com/dw/image/v2/BCKX_PRD/on/demandware.static/-/Sites-masterCatalog_Sweet/default/dw10df661e/images/hi-res/835013_Elbow-Guards_BLACK_PRODUCT_1_Sweetprotection.jpg?sw=628&sh=618"),
      Map.entry("Headband",
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwU9XzD-U1NKYAxpMODZHHKYVsZfghsauiZvWe8_PDx19MY6wSk-U0ljjzWG-Zi5nAB5s&usqp=CAU"),
      Map.entry("Wristband",
          "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/dcxxfifsfjtwpeelld43/swoosh-wristbands-CwPs55.jpg"),
      Map.entry("Hoodie",
          "https://cdn.shopify.com/s/files/1/0258/9160/2490/products/4200918-9134-fr_chrome-sport-hoodie-web_2000x2000.jpg?v=1614601349"),
      Map.entry("Flip Flop",
          "https://www.orthoticshop.com/images/magictoolbox_cache/5d599ff3f9afd17d2a191ee4d861bf84/3/2/3279/thumb600x500/2017052391/sole-sport-flips-men-Raven_alt_front.jpg"),
      Map.entry("Pool Noodle", "https://m.media-amazon.com/images/I/81J6gHdjMaL._AC_SL1500_.jpg")
  );

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
   * Generates a random brand
   *
   * @return - a string brand
   */
  public static String getBrand() {
    Random randomGenerator = new Random();
    return brands[randomGenerator.nextInt(brands.length)];
  }

  /**
   * Generates a random material
   *
   * @return - a string material
   */
  public static String getMaterial() {
    Random randomGenerator = new Random();
    return materials[randomGenerator.nextInt(materials.length)];
  }

  /**
   * Gets an image based on type
   *
   * @return - a string url
   */
  public static String getImage(String type) {
    return images.get(type);
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
   * Generates a random quantity number
   *
   * @return - a quantity integer
   */
  public static int getQuantity() {
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(251);
  }

  /**
   * Generates a random price
   *
   * @param min - the beginning bound
   * @param max - the end bound
   * @return - a price as double with a .00 precision
   */
  public static double getPrice(double min, double max) {
    Random randomGenerator = new Random();
    double randomPrice = randomGenerator.nextDouble() * (max - min) + min;
    DecimalFormat decimalFormatter = new DecimalFormat("#0.00");
    double formattedPrice = Double.valueOf(decimalFormatter.format(randomPrice));

    return formattedPrice;
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
    String brand = ProductFactory.getBrand();
    String material = ProductFactory.getMaterial();
    String image = ProductFactory.getImage(type);
    int quantity = ProductFactory.getQuantity();
    double price = ProductFactory.getPrice(10.00D, 999.99D);

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
    product.setBrand(brand);
    product.setMaterial(material);
    product.setImageSrc(image);
    product.setQuantity(quantity);
    product.setPrice(price);

    return product;
  }
}
