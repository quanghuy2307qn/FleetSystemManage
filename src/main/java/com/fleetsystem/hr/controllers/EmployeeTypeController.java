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
import com.fleetsystem.hr.models.EmployeeType;
import com.fleetsystem.hr.services.EmployeeTypeService;

@Controller
public class EmployeeTypeController {
	@Autowired
	private EmployeeTypeService employeeTypeService;
	
	
	@GetMapping("/employeeTypes")
	public String getAll(Model model) {
		List<EmployeeType> employeeTypes = employeeTypeService.findAll();
		model.addAttribute("emloyeeTypes",employeeTypes);
		return "hr/employeeType/employeeTypeList";
	}
	 //Get Job Title by id
    @GetMapping("/employeeType/{id}")
    @ResponseBody
    public EmployeeType getById(@PathVariable Integer id){
        return employeeTypeService.findById(id).orElse(null);
    }

    @PostMapping("/saveEmployeeType")
    public String save(EmployeeType employeeType){
        employeeTypeService.save(employeeType);
        return "redirect:/employeeTypes";
    }

    @RequestMapping(value="/employeeType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        employeeTypeService.delete(id);
        return "redirect:/employeeTypes";
    }
	
	
}
