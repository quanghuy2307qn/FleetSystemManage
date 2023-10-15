package com.fleetsystem.parameters.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.services.CountryService;

@Controller
public class CountryController {
	@Autowired
	private CountryService countryService;
	
	//@GetMapping("/countries")
	//public String getAll(Model model,String keyword){
		
	//	List<Country> countries;
		//countries = keyword == null ? countryService.getAll() : countryService.findByKeyword(keyword);
		//model.addAttribute("countries",countries);
		//return "parameters/countryList";
	//}
	
	@GetMapping("/countries/page/{pageNumber}")
	public String getOnePage(Model model,@PathVariable("pageNumber") int currentPage,String keyword) {
		Page<Country> page = countryService.getPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Country> countries;
		if(keyword==null) {
			countries = page.getContent();
		}else {
			page=countryService.findPageByKeyword(keyword, currentPage);
			countries=page.getContent();
		}
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		model.addAttribute("countries",countries);
		
		return "/parameters/country/countryList";
		
	}
	
	@GetMapping("/countries")
	public String getAllPages(Model model,String keyword) {
		return getOnePage(model,1,keyword);
	}
	
	@GetMapping("/countries/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model, @PathVariable("field") String field,
			@RequestParam(name="sortDir",defaultValue = "asc") String sortDir,
			@PathVariable("pageNumber") int currentPage){
		
		Page<Country> page;
		page = countryService.getAllWithSort(field, sortDir,currentPage);
		List<Country> countries = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
	
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		
		
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc" : "asc" ); 
		
		model.addAttribute("countries",countries);
		return "parameters/country/countryList";
	}
	@GetMapping("/countryAdd")
	public String addCountry(Model model) {
		
		
		
		return "parameters/country/countryAdd";
	}
	
	//The Get Country By Id
	@GetMapping("/parameters/country/{id}")
	@ResponseBody
	public Country getCountry(@PathVariable Integer id){
	    return countryService.getById(id);
	}
	
	
	@RequestMapping(value="/countries/edit/{id}",method=RequestMethod.GET)
	public String editCountry(Model model,@PathVariable Integer id) {
		Country country = countryService.getById(id);
		
		model.addAttribute("country",country);
		return "parameters/country/countryEdit";
	}
	
	@RequestMapping(value="/countries/detail/{id}",method=RequestMethod.GET)
	public String detailCountry(Model model,@PathVariable Integer id) {
		Country country = countryService.getById(id);
		
		model.addAttribute("country",country);
		return "parameters/country/countryDetails";
	}
	
	@PostMapping("/save")
	public String save(Country country) {
		countryService.save(country);
		return "redirect:/countries";
	}
	
	@RequestMapping(value = "/countries/delete/{id}",method= {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Integer id) {
		countryService.delete(id);
		return "redirect:/countries";
	}
	
	@RequestMapping(value = "/countries/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,Country country) {
		countryService.update(country, id);
		return "redirect:/countries";
	}
	
}
