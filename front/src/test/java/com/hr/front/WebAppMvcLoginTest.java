package com.hr.front;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
//import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
//import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebAppMvcLoginTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	
	/*@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	
	@Test
	public void testShowLoginPage() throws Exception {
		mockMvc.perform(get("/login"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testLoginPageWithGoodAuthent() throws Exception {
		mockMvc.perform(formLogin("/login")
		.user("admin")
		.password("admin"))
		.andDo(print())
		.andExpect(authenticated());
	}
	
	@Test
	public void testLoginPageWithWrongPassword() throws Exception {
		mockMvc.perform(formLogin("/login")
				.user("admin")
				.password("wrong"))
				.andDo(print())
				.andExpect(unauthenticated());
	}*/
}
