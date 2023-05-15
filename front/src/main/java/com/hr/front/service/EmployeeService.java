package com.hr.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.front.model.Employee;
import com.hr.front.repository.EmployeeProxy;

import lombok.Data;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final Long id) {
        return employeeProxy.getEmployeeById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final Long id) {
        employeeProxy.deleteEmployee(id);;
    }

     public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;

        employee.setLastName(employee.getLastName().toUpperCase());

        if(employee.getId() == null) {
            // Si l'id est nul, alors c'est un nouvel employé.
            savedEmployee = employeeProxy.saveEmployee(employee);
        } else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }
    
        return savedEmployee;
    }

}
