package com.hr.front.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.hr.front.model.Employee;
import com.hr.front.service.EmployeeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@WithMockUser("admin")
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeService employeeService;

	@Test
	public void testHome() throws Exception {
		doReturn(buildListEmployees()).when(employeeService).getEmployees();
		mockMvc.perform(get("/"))
		.andDo(print())
		.andExpect(content().string(containsString("Laurent")));
	}

	@Test
	public void testCreateEmployee() throws Exception {
		mockMvc.perform(get("/createEmployee"))
		.andDo(print())
		.andExpect(content().string(containsString("Ajout d'un nouvel employée")));
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		doReturn(buildEmployee("Laurent", "SYMPA",
				"laurent.sympa@mail.com", "123456")).when(employeeService).getEmployee(Long.valueOf(1));
		mockMvc.perform(get("/updateEmployee/1"))
		.andDo(print())
		.andExpect(content().string(containsString("Modification d'un employée")))
		.andExpect(content().string(containsString("Laurent")));
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		doNothing().when(employeeService).deleteEmployee(Long.valueOf(1));
		mockMvc.perform(get("/deleteEmployee/1"))
		.andDo(print())
		.andReturn();
	}

	@Test
	public void testSaveEmployee() throws Exception {
		Employee employee = buildEmployee("Laurent", "SYMPA",
				"laurent.sympa@mail.com", "123456");
		doNothing().when(employeeService).deleteEmployee(Long.valueOf(1));
		mockMvc.perform(post("/saveEmployee").flashAttr("employee", employee))
		.andDo(print())
		.andReturn();
	}

	@Test
	public void testSaveEmployeeWithId() throws Exception {
		Employee employee = buildEmployee("Laurent", "SYMPA",
				"laurent.sympa@mail.com", "123456");
		employee.setId(Long.valueOf(1));
		doNothing().when(employeeService).deleteEmployee(Long.valueOf(1));
		mockMvc.perform(post("/saveEmployee").flashAttr("employee", employee))
		.andDo(print())
		.andReturn();
	}

	private ArrayList<Employee> buildListEmployees() {
		ArrayList<Employee> lEmployees = new ArrayList<Employee>();
		Employee employee = new Employee();
		employee.setFirstName("Laurent");
		employee.setLastName("SYMPA");
		lEmployees.add(buildEmployee("Laurent", "SYMPA",
				"laurent.sympa@mail.com", "123456"));
		return lEmployees;
	}

	private Employee buildEmployee(String firstName, String lastName,
			String mail, String password) {
		Employee empl = new Employee();
		empl.setLastName(lastName);
		empl.setFirstName(firstName);
		empl.setMail(mail);
		empl.setPassword(password);
		return empl;
	}
}
