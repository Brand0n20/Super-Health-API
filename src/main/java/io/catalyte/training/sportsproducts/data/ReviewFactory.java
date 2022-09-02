package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.reviews.Review;
import io.catalyte.training.sportsproducts.domains.user.User;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * class to generate random reviews to the database
 */
public class ReviewFactory {

  private static final String[] comments = {
      "After using this Product my business skyrocketed! I like this Product more and more each day because it makes my life a lot easier.",
      "This Product has got everything I need. Thank you so much for your help. Needless to say we are extremely satisfied with the results. We're loving it",
      "Thanks this Product!",
      "I didn't even need training. It's really wonderful.",
      "I love this Product. This Product was worth a fortune to my company. This Product was the best investment I ever made. Best. Product. Ever!",
      "Nice work on your this Product. After using this Product my business skyrocketed!",
      "We've used this Product for the last five years. Thanks guys, keep up the good work!",
      "If you want real marketing that works and effective implementation - this Product's got you covered.",
      "",
      "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestib",
  };

  private static final String[] titles = {
      "What Everyone Must Know About PRODUCT",
      "Why You Really Need This PRODUCT",
      "Far far away, behind the word mountains, far from.",
      "5 Brilliant Ways To Use PRODUCT",
      "Use PRODUCT To Make Someone Fall In Love With You",
      "Don't Fall For This PRODUCT Scam",
      "Why this PRODUCT Fails",
      "Want More Money? Start PRODUCT",
      "No More Mistakes With PRODUCT"
  };

  /**
   * Generates a random comment
   *
   * @return a random comment from comments
   */
  private static String getRandomComment() {
    Random randomGenerator = new Random();
    return comments[randomGenerator.nextInt(comments.length)];
  }

  /**
   * Generates a random title
   *
   * @return - a random title from titles
   */
  private static String getRandomTitle() {
    Random randomGenerator = new Random();
    return titles[randomGenerator.nextInt(titles.length)];
  }

  /**
   * Generates a random rating between 1-5
   *
   * @param min - minimum value threshold
   * @param max - maximum value threshold
   * @return - a rating up to one decimal place
   */
  private static double getRandomRating(int min, int max) {
    Random randomGenerator = new Random();
    double randomRating = randomGenerator.nextDouble() * (max - min) + min;
    DecimalFormat decimalFormatter = new DecimalFormat("#0.0");
    return Double.valueOf(decimalFormatter.format(randomRating));
  }

  /**
   * Get a random user to apply a user to a review
   *
   * @param users - List of users
   * @return - a random user from list of users
   */
  public static User getRandomUser(List<User> users) {
    Random randomGenerator = new Random();
    return users.get(randomGenerator.nextInt(users.size() - 1));
  }

  /**
   * Gets a random locale date as the date for which the review is posted
   *
   * @param startInclusive
   * @param endExclusive
   * @return - String date
   */
  private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }

  /**
   * @param products - List of products that were generated
   * @param users    - List of users that were generated
   * @return - List of random reviews for products
   */
  public List<Review> generateRandomReviews(List<Product> products, List<User> users) {
    List<Review> reviews = new ArrayList<>();

    for (Product product : products) {
      Random randomGenerator = new Random();
      int randomNumber = randomGenerator.nextInt(5);
      for (int i = 0; i < randomNumber; i++) {
        User user = ReviewFactory.getRandomUser(users);
        reviews.add(createRandomReview(product, user));
      }
    }
    return reviews;
  }

  /**
   * Generates a random review for a product and user
   *
   * @param product - Product
   * @param user    - Product
   * @return - a random review
   */

  public Review createRandomReview(Product product, User user) {
    Review randomReview = new Review();
    randomReview.setComment(ReviewFactory.getRandomComment());
    randomReview.setRating(ReviewFactory.getRandomRating(1, 5));
    randomReview.setTitle(ReviewFactory.getRandomTitle());
    randomReview.setProduct(product);
    randomReview.setUser(user);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate date = ReviewFactory.between(LocalDate.of(1970, 1, 1), LocalDate.now());
    randomReview.setPostedDate(dateTimeFormatter.format(date));
    return randomReview;
  }

}
