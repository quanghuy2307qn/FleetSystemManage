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
import com.fleetsystem.fleet.service.VehicleStatusService;

@Controller
public class VehicleStatusController {
	@Autowired
	private VehicleStatusService vehicleStatusService;
	
	@GetMapping("/vehicleStatuses")
	public String getAll(Model model) {
		List<VehicleStatus> listVehicleStatus = vehicleStatusService.findAll();
		model.addAttribute("vehicleStatuses",listVehicleStatus);
		return "fleet/vehicleStatus/vehicleStatusList";
	}
	
	@RequestMapping("/vehicleStatus/{id}")
	@ResponseBody
	public Optional<VehicleStatus> findById(@PathVariable Integer id)
	{
		return vehicleStatusService.findById(id);
	}
	
	//Add VehicleStatus
	@PostMapping(value="/vehicleStatuses")
	public String addNew(VehicleStatus vehicleStatus) {
		vehicleStatusService.save(vehicleStatus);
		return "redirect:/vehicleStatuses";
	}	

	@RequestMapping(value="vehicleStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleStatusService.delete(id);
		return "redirect:/vehicleStatuses";
	}
}
