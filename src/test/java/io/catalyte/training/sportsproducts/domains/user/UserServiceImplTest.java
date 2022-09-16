package io.catalyte.training.sportsproducts.domains.user;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  User testUser;

  @InjectMocks
  private UserServiceImpl userServiceImpl;

  @Mock
  private UserRepository userRepository;

  @Before
  public void setUp() {
    User user = new User();

    user.setEmail("Travis@Travis.com");

    // testUser = userRepository.findByEmail();
  }

  @Test
  void updateUser() {
  }

  @Test
  void createUser() {
  }

  @Test
  void getUserByEmail() {
  }
}