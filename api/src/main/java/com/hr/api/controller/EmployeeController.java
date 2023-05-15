package com.hr.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.model.Employee;
import com.hr.api.service.EmployeeService;

@RestController
@RequestMapping("employee/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("{id}")
	public Employee getEmployeeById(@PathVariable final Long id) {
		Optional<Employee> optionalEmployee = employeeService.getEmployee(id);
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		}
		else {
			return null;
		}
	}
	
	@GetMapping("all")
	public Iterable<Employee> getEmployees() {
		return employeeService.getEmployees();
	}
	
	@PostMapping("save")
	public Employee saveEmployees(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@PutMapping("update/{id}")
	public Employee updateEmployees(@PathVariable("id") final Long id, @RequestBody Employee employee) {
		Optional<Employee> opSavedEmployee = employeeService.getEmployee(id);
		if(opSavedEmployee.isPresent()) {
			Employee currentEmployee = opSavedEmployee.get();
			
			String firstName = employee.getFirstName();
			if(firstName != null) {
				currentEmployee.setFirstName(firstName);
			}
			String lastName = employee.getLastName();
			if(lastName != null) {
				currentEmployee.setLastName(lastName);;
			}
			String mail = employee.getMail();
			if(mail != null) {
				currentEmployee.setMail(mail);
			}
			String password = employee.getPassword();
			if(password != null) {
				currentEmployee.setPassword(password);;
			}
			employeeService.saveEmployee(currentEmployee);
			return currentEmployee;
		} 
		else {
			return null;
		}
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteEmployee(@PathVariable final Long id) {
		employeeService.deleteEmployee(id);
	}

}
