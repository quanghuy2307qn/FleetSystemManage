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
import com.fleetsystem.fleet.model.VehicleHire;
import com.fleetsystem.fleet.service.VehicleHireService;
import com.fleetsystem.fleet.service.VehicleService;
import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.services.ClientService;
import com.fleetsystem.parameters.services.LocationService;

@Controller
public class VehicleHireController {
	@Autowired
	private VehicleHireService vehicleHireService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/vehicleHire")
	public String getAll(Model model) {
		List<VehicleHire> hires = vehicleHireService.findAll();
		model.addAttribute("hires",hires);
		return "fleet/vehicleHire/vehicleHireList";
	}
	@GetMapping("/vehicleHireAdd")
	public String add(Model model) {
		model.addAttribute("vehicles",vehicleService.findAll());
		model.addAttribute("clients",clientService.findAll());
		model.addAttribute("locations",locationService.findAll());
		
		return "fleet/vehicleHire/vehicleHireAdd";
	}
	
	@PostMapping("/saveVehicleHire")
	public String save(VehicleHire vehicleHire) {
		vehicleHireService.save(vehicleHire);
		return "redirect:/vehicleHire";
	}
	
	
	@RequestMapping(value="/hire/edit/{id}",method=RequestMethod.GET)
	public String editVehicleHire(Model model,@PathVariable Integer id) {
		VehicleHire vehicleHire = vehicleHireService.findById(id);
		model.addAttribute("hire",vehicleHire);
		model.addAttribute("vehicles",vehicleService.findAll());
		model.addAttribute("clients",clientService.findAll());
		model.addAttribute("locations",locationService.findAll());
		
		return "fleet/vehicleHire/vehicleHireEdit";
	}
	
	@PostMapping("/updateHire")
	public String updateVehicle(VehicleHire vehicleHire) {
		vehicleHireService.save(vehicleHire);
		return "redirect:/vehicleHire";
	}
	
	@RequestMapping(value="/hire/detail/{id}",method=RequestMethod.GET)
	public String detailVehicleHire(Model model,@PathVariable Integer id) {
		VehicleHire vehicleHire = vehicleHireService.findById(id);
		model.addAttribute("hire",vehicleHire);
		model.addAttribute("vehicles",vehicleService.findAll());
		model.addAttribute("clients",clientService.findAll());
		model.addAttribute("locations",locationService.findAll());
		
		return "fleet/vehicleHire/vehicleHireDetail";
	}
	
	@RequestMapping(value="/hire/delete{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteVehicleHire(@PathVariable Integer id) {
		vehicleHireService.delete(id);
		return "redirect:/vehicleHire";
	}
	
}
