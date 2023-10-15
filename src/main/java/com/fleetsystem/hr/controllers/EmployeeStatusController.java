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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fleetsystem.hr.models.EmployeeStatus;
import com.fleetsystem.hr.services.EmployeeStatusService;

@Controller
public class EmployeeStatusController {
	@Autowired
	private EmployeeStatusService employeeStatusService;
	
	//Display all
		@GetMapping("/employeeStatus")
		public String getAll(Model model) {
			List<EmployeeStatus> listEmployeeStatus = employeeStatusService.findAll();
			model.addAttribute("employeeStatuses",listEmployeeStatus);
			
			return "hr/employeeStatus/employeeStatusList";
		}
		
		 //Get Job Title by id
	    @GetMapping("/employeeStatus/{id}")
	    @ResponseBody
	    public EmployeeStatus getById(@PathVariable Integer id){
	        return employeeStatusService.findById(id).orElse(null);
	    }

	    @PostMapping("/saveEmployeeStatuses")
	    public String save(EmployeeStatus employeeStatus){
	        employeeStatusService.save(employeeStatus);
	        return "redirect:/employeeStatuses";
	    }

	    @RequestMapping(value="/employeeStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	    public String delete(@PathVariable Integer id) {
	        employeeStatusService.delete(id);
	        return "redirect:/employeeStatuses";
	    }
}
