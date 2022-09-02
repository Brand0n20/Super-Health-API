package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 * class to generate users to the database
 */
public class UserFactory {

  private static final String[] names = {
      "Billy Miller",
      "Samantha Scott",
      "Travis Mitchell",
      "Rebecca Schultz",
      "Crystal Gonzalez",
      "Amy Anderson",
      "Richard Montes",
      "Samantha Lam",
      "Rachael Dean",
      "Jessica Gomez",
      "Jeremy Dawson",
      "Brenda Walker",
      "Jeffrey Cuevas",
      "Adam Kelly",
      "Darius Mann",
      "Allison Murphy",
      "Caitlin Reilly",
      "Bryan James",
      "Nicole Green",
      "Albert Bailey"};

  /**
   * @param name - first and last name of a user
   * @return - a random user
   * @Kevin Davis Generates a random user
   */
  public User createUser(String name) {
    User user = new User();
    String[] arrayName = name.split(" ");
    user.setFirstName(arrayName[0]);
    user.setLastName(arrayName[1]);
    user.setRole("Customer");
    user.setEmail(arrayName[0] + "@" + arrayName[0] + ".com");
    return user;
  }

  /**
   * @return - List of users
   * @author Kevin Davis Generates a random list of users
   */

  public List<User> generateUsers() {
    List<User> users = new ArrayList<>();
    for (String name : names) {
      users.add(this.createUser(name));
    }
    return users;
  }
}
