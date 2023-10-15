package com.fleetsystem.fleet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fleetsystem.fleet.model.Vehicle;
import com.fleetsystem.fleet.service.VehicleMakeService;
import com.fleetsystem.fleet.service.VehicleModelService;
import com.fleetsystem.fleet.service.VehicleService;
import com.fleetsystem.fleet.service.VehicleStatusService;
import com.fleetsystem.fleet.service.VehicleTypeService;
import com.fleetsystem.hr.services.EmployeeService;
import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.services.LocationService;

@Controller
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private VehicleTypeService vehicleTypeService;
	@Autowired
	private VehicleMakeService vehicleMakeService;
	@Autowired 
	private VehicleModelService vehicleModelService;
	@Autowired
	private VehicleStatusService vehicleStatusService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/vehicles")
	private String getAll(Model model) {
		List<Vehicle> listVehicles = vehicleService.findAll();
		model.addAttribute("vehicles",listVehicles);
		
		
		return "fleet/vehicle/vehicleList";
	}
	
	@GetMapping("/vehicleAdd")
	private String addVehicle(Model model) {
		model.addAttribute("vehicleTypes",vehicleTypeService.findAll());
		model.addAttribute("vehicleMakes",vehicleMakeService.findAll());
		model.addAttribute("vehicleModels",vehicleModelService.findAll());
		model.addAttribute("vehicleStatuses",vehicleStatusService.findAll());
		model.addAttribute("locations",locationService.findAll());
		model.addAttribute("employees",employeeService.findAll());
		
		return "fleet/vehicle/vehicleAdd";
	}
	
	@PostMapping("/saveVehicles")
	private String saveVehicle(Vehicle vehicle) {
		vehicleService.save(vehicle);
		
		return "redirect:/vehicles";
	}
	
	@RequestMapping(value="/vehicle/edit/{id}",method=RequestMethod.GET)
	public String editVehicle(Model model,@PathVariable Integer id) {
		Vehicle vehicle = vehicleService.findById(id);
		model.addAttribute("vehicle",vehicle);
		
		model.addAttribute("vehicleTypes",vehicleTypeService.findAll());
		model.addAttribute("vehicleMakes",vehicleMakeService.findAll());
		model.addAttribute("vehicleModels",vehicleModelService.findAll());
		model.addAttribute("vehicleStatuses",vehicleStatusService.findAll());
		model.addAttribute("locations",locationService.findAll());
		model.addAttribute("employees",employeeService.findAll());
		
		return "fleet/vehicle/vehicleEdit";
	}
	
	@PostMapping("/updateVehicle")
	public String updateVehicle(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@RequestMapping(value="/vehicle/detail/{id}",method=RequestMethod.GET)
	public String detailVehicle(Model model,@PathVariable Integer id) {
		Vehicle vehicle = vehicleService.findById(id);
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("vehicleTypes",vehicleTypeService.findAll());
		model.addAttribute("vehicleMakes",vehicleMakeService.findAll());
		model.addAttribute("vehicleModels",vehicleModelService.findAll());
		model.addAttribute("vehicleStatuses",vehicleStatusService.findAll());
		model.addAttribute("locations",locationService.findAll());
		model.addAttribute("employees",employeeService.findAll());
		
		return "fleet/vehicle/vehicleDetail";
	}
	
	@RequestMapping(value="/vehicle/delete{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteVehicle(@PathVariable Integer id) {
		vehicleService.delete(id);
		return "redirect:/vehicles";
	}
	
}
