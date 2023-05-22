package com.hr.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hr.front.model.Employee;
import com.hr.front.service.EmployeeService;

import jakarta.annotation.security.RolesAllowed;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String home(Model model) {
		
		Iterable<Employee> employees = employeeService.getEmployees();
		model.addAttribute("employees", employees);
		return "home";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/createEmployee")
	public String createEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "formNewEmployee";
	}

	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable final long id, Model model) {
		Employee employee = employeeService.getEmployee(id);
		model.addAttribute("employee", employee);
		return "formUpdateEmployee";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView home(@PathVariable final long id) {
		
		employeeService.deleteEmployee(id);
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		// Lors d'une modification on ne peut pas modifier le password
		if(employee.getId() != null) {
			Employee currentEmployee = employeeService.getEmployee(employee.getId());
			employee.setPassword(currentEmployee.getPassword());
		}
		employeeService.saveEmployee(employee);
	    return new ModelAndView("redirect:/");
	}
}
