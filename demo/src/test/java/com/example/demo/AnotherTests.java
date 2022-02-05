package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mishelon.base.DemoApplication;
import com.mishelon.base.controllers.UserController;
import com.mishelon.base.dto.UserDTO;

@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema.sql", "classpath:db/populateDB.sql"})
@ActiveProfiles("integrationtest")
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class AnotherTests {
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Autowired
  private MockMvc mvc;

  @Autowired
  UserController userController;

  // @Test
  // public void findKOTest() throws Exception {
  // mvc.perform(get("/findById/12").contentType(MediaType.APPLICATION_JSON))
  // .andExpect(status().isNotFound());
  // }
  //
  // @Test
  // public void findOkTest() throws Exception {
  // mvc.perform(get("/findById/1").contentType(MediaType.APPLICATION_JSON))
  // .andExpect(status().isOk())
  // .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
  // .andExpect(jsonPath("$.name", is("Francisco")));
  // }

  @Test
  public void updateTest() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post("/user/update")
        .content(asJsonString(new UserDTO(UUID.randomUUID().toString(), "mishelon", "a", "b")))
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
  }
}
