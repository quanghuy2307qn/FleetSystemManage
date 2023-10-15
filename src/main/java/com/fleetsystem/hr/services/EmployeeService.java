package com.fleetsystem.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetsystem.hr.models.Employee;
import com.fleetsystem.hr.repositories.EmployeeRepository;
import com.fleetsystem.parameters.models.Client;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	// Get All Client
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	// Get Employee By Id
	public Employee findById(int id) {
		return employeeRepository.findById(id).orElse(null);
	}

	// Save employee
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}


	// Delete Employee
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
}
