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
import com.fleetsystem.fleet.model.VehicleMovement;
import com.fleetsystem.fleet.service.VehicleMovementService;
import com.fleetsystem.fleet.service.VehicleService;
import com.fleetsystem.parameters.models.Location;
import com.fleetsystem.parameters.services.LocationService;

@Controller
public class VehicleMovementController {
	@Autowired
	private VehicleMovementService vehicleMovementService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private LocationService locationService;

	@GetMapping("/movements")
	public String getAll(Model model) {
		List<VehicleMovement> listVehicleMovements = vehicleMovementService.findAll();
		model.addAttribute("movements", listVehicleMovements);

		return "fleet/vehicleMovement/vehicleMovementList";
	}

	@GetMapping("/movementAdd")
	private String add(Model model) {
		model.addAttribute("locations1", locationService.findAll());
		model.addAttribute("locations2", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
		return "fleet/vehicleMovement/vehicleMovementAdd";
	}

	@PostMapping("/saveMovements")
	private String save(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);

		return "redirect:/movements";
	}

	@RequestMapping(value = "/movement/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Integer id) {
		VehicleMovement vehicleMovement = vehicleMovementService.findById(id);
		model.addAttribute("movement", vehicleMovement);
		model.addAttribute("locations1", locationService.findAll());
		model.addAttribute("locations2", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
		return "fleet/vehicleMovement/vehicleMovementEdit";
	}

	@PostMapping("/updateMovement")
	public String update(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/movements";
	}

	@RequestMapping(value = "/movement/detail/{id}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable Integer id) {
		VehicleMovement vehicleMovement = vehicleMovementService.findById(id);
		model.addAttribute("movement", vehicleMovement);
		model.addAttribute("locations1", locationService.findAll());
		model.addAttribute("locations2", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
		return "fleet/vehicleMovement/vehicleMovementDetail";
	}

	@RequestMapping(value = "/movement/delete{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delete(@PathVariable Integer id) {
		vehicleMovementService.delete(id);
		return "redirect:/movements";
	}

}
