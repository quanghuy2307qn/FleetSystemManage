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

import com.fleetsystem.fleet.model.VehicleStatus;
import com.fleetsystem.fleet.model.VehicleType;
import com.fleetsystem.fleet.service.VehicleTypeService;

@Controller
public class VehicleTypeController {
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@GetMapping("/vehicleTypes")
	public String getAll(Model model) {
		List<VehicleType> listVehicleTypes = vehicleTypeService.findAll();
		model.addAttribute("vehicleTypes",listVehicleTypes);
		return "fleet/vehicleType/vehicleTypeList";
	}
	
	@RequestMapping("/vehicleType/{id}")
	@ResponseBody
	public Optional<VehicleType> findById(@PathVariable Integer id)
	{
		return vehicleTypeService.findById(id);
	}
	
	//Save
	@PostMapping(value="/saveVehicleTypes")
	public String addNew(VehicleType vehicleType) {
		vehicleTypeService.save(vehicleType);
		return "redirect:/vehicleTypes";
	}	

	@RequestMapping(value="vehicleType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleTypeService.delete(id);
		return "redirect:/vehicleTypes";
	}
}
