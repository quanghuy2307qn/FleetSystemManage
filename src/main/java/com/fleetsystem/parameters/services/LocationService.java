package com.fleetsystem.parameters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.models.Location;
import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.models.Supplier;
import com.fleetsystem.parameters.repositories.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	//Get All States
	public List<Location> findAll(){
		return locationRepository.findAll();
	}
	
	//Get State by Id
	public Location findById(int id) {
		return locationRepository.findById(id).orElse(null);
	}
	
	//Delete State 
	public void delete(Integer id) {
		locationRepository.deleteById(id);
	}
	
	//Update State
	public Location update(Location location,Integer id) {
		Location fromDb = locationRepository.findById(id).orElse(null);
		if(fromDb == null) {
			return null;
		}
		
		fromDb.setDescription(location.getDescription());
		fromDb.setDetails(location.getDetails());
		fromDb.setCountry(location.getCountry());
		fromDb.setCountryid(location.getCountryid());
		fromDb.setState(location.getState());
		fromDb.setStateid(location.getStateid());
		fromDb.setAddress(location.getAddress());
		fromDb.setCity(location.getCity());
		return locationRepository.save(fromDb);
	}
	
	//Save State
	public void save(Location location) {
		locationRepository.save(location);
	}
	
	public List<Location> findByKeyword(String keyword){
		return locationRepository.getByKeyword(keyword);
	}
	public Page<Location> getPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return locationRepository.findAll(pageable);
	}
	public Page<Location> findPageByKeyword(String keyword,int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return locationRepository.getPageByKeyword(keyword, pageable);
	}
	public Page<Location> getAllWithSort(String field,String direction,int pageNumber){
		//asc or desc
		
		Sort sort = Sort.by(Sort.Direction.ASC.name());
		if(direction.equalsIgnoreCase(Sort.Direction.ASC.name())) {
			sort=Sort.by(field).ascending();
		}else {
			sort=Sort.by(field).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber-1,5,sort);
		return locationRepository.findAll(pageable);
		
	}
}
