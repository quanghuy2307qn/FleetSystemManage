package com.fleetsystem.fleet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetsystem.fleet.model.Vehicle;
import com.fleetsystem.fleet.repository.VehicleRepository;
import com.fleetsystem.parameters.models.Location;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;

	// Get All Vehicles
	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	// Get Vehicle By Id
	public Vehicle findById(int id) {
		return vehicleRepository.findById(id).orElse(null);
	}

	// Delete Vehicle
	public void delete(int id) {
		vehicleRepository.deleteById(id);
	}

	// Save Vehicle
	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
	

	
}
