package io.catalyte.training.superhealthapi.domains.user;

import static io.catalyte.training.superhealthapi.constants.Paths.USERS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class UserApiTest {

  @Autowired
  UserServiceImpl userService;

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void CreateUser() throws Exception {
    User user = new User(23L, "RickyG@gmail.com", "Customer", "Ryan", "Yoshida", "6523 W Chelsea",
        "Manhattan", "New York", "56702", "3 hours ago");
    this.mockMvc.perform(post(USERS_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(user)).header("Authorization", "Bearer"))
        .andExpect(status().isCreated());
  }

//  @Test
//  public void updateUser() throws Exception {
//    User user = new User();
//    user.setFirstName("Randy");
//    user.setId(23L);
//    given(userService.updateUser("Bearer Randy", user.getId(), user)).willReturn(user);
//
//    this.mockMvc.perform(put(USERS_PATH + "/" + user.getId().toString()).contentType(MediaType.APPLICATION_JSON)
//        .content(asJsonString(user))).andExpect(status().isAccepted()).andExpect(
//        (ResultMatcher) jsonPath("firsName", is(user.getFirstName())));
//  }

  @Test
  public void getUserByEmailReturns200Status() throws Exception {
    mockMvc.perform(get(USERS_PATH + "/Travis@Travis.com"))
        .andExpect(status().isOk());
  }
}