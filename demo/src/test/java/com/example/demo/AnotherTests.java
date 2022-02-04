package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.mishelon.base.DemoApplication;
import com.mishelon.base.controllers.UserController;

@SpringBootTest(classes = {DemoApplication.class} )
@AutoConfigureMockMvc
@Sql(scripts = "classpath:db/populateDB.sql")
public class AnotherTests {
	@Autowired
    private MockMvc mvc;

    @Autowired
    UserController userController;
    
	@Test
    public void findOkTest() throws Exception {
		mvc.perform(get("/findById/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.name", is("Francisco")));
    }
	
	@Test
    public void findKOTest() throws Exception {
		mvc.perform(get("/findById/12")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound());
    }
}
