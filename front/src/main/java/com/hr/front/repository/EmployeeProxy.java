package com.hr.front.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.hr.front.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {
	
	@Autowired
	private CustomProperties props;
	
	public Iterable<Employee> getEmployees() {
		StringBuilder uriBuilder = new StringBuilder();
		uriBuilder.append(props.getApiUrl())
				  .append("/employee/all");
		RestTemplate restTemplate = getResTemplate();
		
		ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(uriBuilder.toString(),
				HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<Employee>>() {} );
		
		log.debug("Get Employees call " + response.getStatusCode().toString());
		
		return response.getBody();
				
	}
	
	public Employee getEmployeeById(Long id) {
		StringBuilder uriBuilder = new StringBuilder();
		uriBuilder.append(props.getApiUrl())
				  .append("/employee/"+ id);
		RestTemplate restTemplate = getResTemplate();
		
		ResponseEntity<Employee> response = restTemplate.exchange(uriBuilder.toString(),
				HttpMethod.GET, null,
				Employee.class);
		
		log.debug("Get Employee call " + response.getStatusCode().toString());
		
		return response.getBody();
				
	}
	
	public Employee saveEmployee(Employee employee) {
		StringBuilder uriBuilder = new StringBuilder();
		uriBuilder.append(props.getApiUrl())
				  .append("/employee/save");
		RestTemplate restTemplate = getResTemplate();
		HttpEntity<Employee> httpEntity = new HttpEntity<Employee>(employee);
		
		ResponseEntity<Employee> response = restTemplate.exchange(uriBuilder.toString(),
				HttpMethod.POST,
				httpEntity,
				Employee.class);
		
		log.debug("Create employee call " + response.getStatusCode().toString());
		
		return response.getBody();
				
	}
	
	public Employee updateEmployee(Employee employee) {
		StringBuilder uriBuilder = new StringBuilder();
		uriBuilder.append(props.getApiUrl())
				  .append("/employee/update/")
				  .append(employee.getId());
		RestTemplate restTemplate = getResTemplate();
		HttpEntity<Employee> httpEntity = new HttpEntity<Employee>(employee);
		
		ResponseEntity<Employee> response = restTemplate.exchange(uriBuilder.toString(),
				HttpMethod.PUT,
				httpEntity,
				Employee.class);
		
		log.debug("Modify employee call " + response.getStatusCode().toString());
		
		return response.getBody();
				
	}
	
	public void deleteEmployee(Long id) {
		StringBuilder uriBuilder = new StringBuilder();
		uriBuilder.append(props.getApiUrl())
				  .append("/employee/delete/"+id);
		RestTemplate restTemplate = getResTemplate();
		
		ResponseEntity response = restTemplate.exchange(uriBuilder.toString(),
				HttpMethod.DELETE,
				null,
				ResponseEntity.class);
		
		log.debug("delete employee call " + response.getStatusCode().toString());
						
	}

	protected RestTemplate getResTemplate() {
		return new RestTemplate();
	}

}
