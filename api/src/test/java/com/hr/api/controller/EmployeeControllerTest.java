package com.hr.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.api.model.Employee;
import com.hr.api.service.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	
	@Test
	public void testGetEmployees() throws Exception {
		mockMvc.perform(get("/employee/all"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmployeeById() throws Exception {
		mockMvc.perform(get("/employee/1"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void testSaveEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Amadou");
		employee.setLastName("DIOUF");
		employee.setMail("amadou@mail.com");
		employee.setPassword("monpassword");
		
		ObjectMapper mapper = new ObjectMapper(); 
		mockMvc.perform(post("/employee/save")
				.content(mapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		Employee employee = new Employee();
		//employee.setId(Long.valueOf(1));
		employee.setFirstName("Charlene");
		employee.setLastName("DIOUF");
		employee.setMail("charléne@mail.com");
		employee.setPassword("monpassword");
		
		ObjectMapper mapper = new ObjectMapper(); 
		mockMvc.perform(put("/employee/update/1")
				.content(mapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		
		mockMvc.perform(delete("/employee/delete/1"))
		.andExpect(status().isOk());
	}
}
