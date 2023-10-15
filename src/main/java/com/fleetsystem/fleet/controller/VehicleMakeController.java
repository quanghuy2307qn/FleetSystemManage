package com.fleetsystem.fleet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fleetsystem.fleet.model.VehicleMake;
import com.fleetsystem.fleet.service.VehicleMakeService;

@Controller
public class VehicleMakeController {
	@Autowired
	private VehicleMakeService vehicleMakeService;
	
	@GetMapping("/vehicleMakes")
	private String getAll(Model model) {
		List<VehicleMake> vehicleMakes = vehicleMakeService.findAll();
		model.addAttribute("vehicleMakes",vehicleMakes);
		
		return "fleet/vehicleMake/vehicleMakeList";
	}
	
	@RequestMapping("/vehicleMakes/{id}")
	@ResponseBody
	public Optional<VehicleMake> findById(@PathVariable Integer id)
	{
		return vehicleMakeService.findById(id);
	}
	
	//Add VehicleMake
	@PostMapping(value="/saveVehicleMake")
	public String addNew(VehicleMake vehicleMake) {
		vehicleMakeService.save(vehicleMake);
		return "redirect:/vehicleMakes";
	}
	
	@RequestMapping(value="vehicleMake/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleMakeService.delete(id);
		return "redirect:/vehicleMakes";
	}
}
