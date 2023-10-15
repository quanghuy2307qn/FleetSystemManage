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
import com.fleetsystem.fleet.model.VehicleModel;
import com.fleetsystem.fleet.service.VehicleModelService;

@Controller
public class VehicleModelController {
	@Autowired
	private VehicleModelService vehicleModelService;
	
	@GetMapping("/vehicleModels")
	public String getAll(Model model) {
		List<VehicleModel> listVehicleModels = vehicleModelService.findAll();
		model.addAttribute("vehicleModels",listVehicleModels);
		return "fleet/vehicleModel/vehicleModelList";
	}
	
	@RequestMapping("/vehicleModel/{id}")
	@ResponseBody
	public Optional<VehicleModel> findById(@PathVariable Integer id)
	{
		return vehicleModelService.findById(id);
	}
	
	//Add VehicleMake
	@PostMapping(value="/saveVehicleModel")
	public String addNew(VehicleModel vehicleModel) {
		vehicleModelService.save(vehicleModel);
		return "redirect:/vehicleModels";
	}
	
	@RequestMapping(value="vehicleModel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleModelService.delete(id);
		return "redirect:/vehicleModels";
	}
}
