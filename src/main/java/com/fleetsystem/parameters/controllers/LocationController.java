package com.fleetsystem.parameters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.models.Location;
import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.services.CountryService;
import com.fleetsystem.parameters.services.LocationService;
import com.fleetsystem.parameters.services.StateService;

@Controller
public class LocationController {
	@Autowired
	public LocationService locationService;
	@Autowired
	public StateService stateService;
	@Autowired
	public CountryService countryService;
	
	//@GetMapping("/locations")
	//public String getAll(Model model,String keyword){
		
		//List<Location> locations;
		//locations = keyword == null ? locationService.findAll() : locationService.findByKeyword(keyword);
		//model.addAttribute("locations",locations);
		//return "parameters/locationList";
	//}
	
	@GetMapping("/locations/page/{pageNumber}")
	public String getOnePage(Model model,@PathVariable("pageNumber") int currentPage,String keyword) {
		Page<Location> page = locationService.getPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Location> locations;
		if(keyword==null) {
			locations = page.getContent();
		}else {
			page=locationService.findPageByKeyword(keyword, currentPage);
			locations=page.getContent();
		}
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		model.addAttribute("locations",locations);
		
		return "/parameters/location/locationList";
		
	}
	
	@GetMapping("/locations")
	public String getAllPages(Model model,String keyword) {
		return getOnePage(model,1,keyword);
	}
	
	@GetMapping("/locations/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model, @PathVariable("field") String field,
			@RequestParam(value="sortDir",defaultValue = "ASC") String sortDir,
			@PathVariable("pageNumber") int currentPage){
		
		Page<Location> page;
		page = locationService.getAllWithSort(field, sortDir,currentPage);
		List<Location> locations = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		
		
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc" : "asc" ); 
		
		model.addAttribute("locations",locations);
		return "parameters/location/locationList";
	}
	
	@GetMapping("/locationAdd")
	public String addLocation(Model model) {
		
		model.addAttribute("countries",countryService.getAll());
		return "parameters/location/locationAdd";
	}
	
	
	@PostMapping("/locationSave")
	public String saveLocation(Location location) {
		locationService.save(location);
		return "redirect:/locations";
	}
	
	@RequestMapping(value="/locations/edit/{id}",method=RequestMethod.GET)
	public String editLocation(Model model,@PathVariable Integer id) {
		Location location = locationService.findById(id);
		model.addAttribute("location",location);
		
		model.addAttribute("countries",countryService.getAll());
		return "parameters/location/locationEdit";
	}
	
	@RequestMapping(value = "/locations/update/{id}",method=RequestMethod.POST)
	public String updateLocation(@PathVariable("id") Integer id,Location location) {
		locationService.save(location);
		return "redirect:/locations";
	}
	
	@RequestMapping(value = "/locations/delete/{id}",method= {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteLocation(@PathVariable Integer id) {
		locationService.delete(id);
		return "redirect:/locations";
	}
	
	@RequestMapping(value="/locations/details/{id}",method=RequestMethod.GET)
	public String detailState(Model model,@PathVariable Integer id) {
		Location location = locationService.findById(id);
		
		model.addAttribute("location",location);
		return "parameters/location/locationDetails";
	}
	
}
