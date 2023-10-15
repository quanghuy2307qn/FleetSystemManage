package com.fleetsystem.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fleetsystem.hr.models.Employee;
import com.fleetsystem.hr.services.EmployeeService;
import com.fleetsystem.hr.services.EmployeeTypeService;
import com.fleetsystem.hr.services.JobTitleService;
import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.models.Location;
import com.fleetsystem.parameters.services.CountryService;
import com.fleetsystem.parameters.services.StateService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private StateService stateService;
	@Autowired
	private EmployeeTypeService employeeTypeService;
	@Autowired
	private JobTitleService jobTitleService;
	
	
	//Display all
	@GetMapping("/employees")
	public String getAll(Model model) {
		List<Employee> listEmployees = employeeService.findAll();
		model.addAttribute("employees",listEmployees);
		
		return "hr/employee/employeeList";
	}
	
	//Add
	@GetMapping("/employeeAdd")
	public String addEmployee(Model model) {
		model.addAttribute("employeeTypes",employeeTypeService.findAll());
		model.addAttribute("jobTitles",jobTitleService.findAll());
		model.addAttribute("countries",countryService.getAll());
		return "/hr/employee/employeeAdd";
	}
	//Save
	@PostMapping("/employeeSave")
	public String save(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}
	
	//Edit 
	@RequestMapping(value="/employee/edit/{id}",method=RequestMethod.GET)
	public String editEmployee(Model model,@PathVariable Integer id) {
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee",employee);
		
		model.addAttribute("countries",countryService.getAll());
		return "parameters/hr/employeeEdit";
	}
	//Save after edit
	@RequestMapping(value = "/employee/update/{id}",method=RequestMethod.POST)
	public String updateEmployee(@PathVariable("id") Integer id,Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}
	//Delete
	@RequestMapping(value="/employee/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		employeeService.delete(id);
		return "redirect:/employees";
	}
	//View detail
	@RequestMapping(value="/employee/details/{id}",method=RequestMethod.GET)
	public String detailEmployee(Model model,@PathVariable Integer id) {
		Employee employee = employeeService.findById(id);
		
		model.addAttribute("employee",employee);
		return "parameters/hr/locationDetails";
	}
}
