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

import com.fleetsystem.fleet.model.VehicleHire;
import com.fleetsystem.fleet.model.VehicleMaintenance;
import com.fleetsystem.fleet.service.VehicleMaintenanceService;
import com.fleetsystem.fleet.service.VehicleService;
import com.fleetsystem.parameters.services.SupplierService;

@Controller
public class VehicleMaintenanceController {
	@Autowired
	private VehicleMaintenanceService vehicleMaintenanceService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private SupplierService supplierService;
	
	
	@GetMapping("/maintenances")
	public String getAll(Model model) {
		List<VehicleMaintenance> maintenance = vehicleMaintenanceService.findAll();
		model.addAttribute("maintenances",maintenance);
		return "fleet/vehicleMaintenance/vehicleMaintenanceList";
		
	}
	@GetMapping("/maintenanceAdd")
	public String add(Model model) {
		model.addAttribute("suppliers",supplierService.findAll());
		model.addAttribute("vehicles",vehicleService.findAll());
		
		return "fleet/vehicleMaintenance/vehicleMaintenanceAdd";
	}
	
	@PostMapping("/saveVehicleMaintenance")
	public String save(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/maintenances";
	}
	
	
	@RequestMapping(value="/maintenance/edit/{id}",method=RequestMethod.GET)
	public String editVehicleMaintenance(Model model,@PathVariable Integer id) {
		VehicleMaintenance vehicleMaintenance = vehicleMaintenanceService.findById(id);
		model.addAttribute("maintenance",vehicleMaintenance);
		model.addAttribute("suppliers",supplierService.findAll());
		model.addAttribute("vehicles",vehicleService.findAll());
		
		return "fleet/vehicleMaintenance/vehicleMaintenanceEdit";
	}
	
	@PostMapping("/updateMaintenance")
	public String updateVehicleMaintenance(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/maintenances";
	}
	
	@RequestMapping(value="/maintenance/details/{id}",method=RequestMethod.GET)
	public String detailVehicleMaintenance(Model model,@PathVariable Integer id) {
		VehicleMaintenance vehicleMaintenance = vehicleMaintenanceService.findById(id);
		model.addAttribute("maintenance",vehicleMaintenance);
		model.addAttribute("suppliers",supplierService.findAll());
		model.addAttribute("vehicles",vehicleService.findAll());
		
		return "fleet/vehicleMaintenance/vehicleMaintenanceDetail";
	}
	
	@RequestMapping(value="/maintenance/delete{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteVehicleMaintenance(@PathVariable Integer id) {
		vehicleMaintenanceService.delete(id);
		return "redirect:/maintenance";
	}
}
