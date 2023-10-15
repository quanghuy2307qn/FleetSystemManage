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
import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.models.Supplier;
import com.fleetsystem.parameters.repositories.StateRepository;
import com.fleetsystem.parameters.services.CountryService;
import com.fleetsystem.parameters.services.StateService;

@Controller
public class StateController {
	
	@Autowired
	public StateService stateService;
	@Autowired
	public CountryService countryService;
	
	//@GetMapping("/states")
	//public String getAll(Model model,String keyword) {
		//List<State> listStates;
		//listStates=keyword==null ?  stateService.findAll() : stateService.findByKeyword(keyword);
		
		//model.addAttribute("states",listStates);
		//return "parameters/stateList";
				
				
	//}
	
	@GetMapping("/states/page/{pageNumber}")
	public String getOnePage(Model model,@PathVariable("pageNumber") int currentPage,String keyword) {
		Page<State> page = stateService.getPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<State> states;
		if(keyword==null) {
			states = page.getContent();
		}else {
			page=stateService.findPageByKeyword(keyword, currentPage);
			states=page.getContent();
		}
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		model.addAttribute("states",states);
		
		return "/parameters/state/stateList";
		
	}
	
	@GetMapping("/states")
	public String getAllPages(Model model,String keyword) {
		return getOnePage(model,1,keyword);
	}
	
	@GetMapping("/states/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model, @PathVariable("field") String field,
			@RequestParam(value="sortDir",defaultValue = "ASC") String sortDir,
			@PathVariable("pageNumber") int currentPage){
		
		Page<State> page;
		page = stateService.getAllWithSort(field, sortDir,currentPage);
		List<State> states = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		
		
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc" : "asc" ); 
		
		model.addAttribute("states",states);
		return "parameters/state/stateList";
	}
	
	@GetMapping("/stateAdd")
	public String addState(Model model) {
		model.addAttribute("countries",countryService.getAll());
		return "parameters/state/stateAdd";
	}
	
	@PostMapping("/saveStates")
	public String save(State state) {
		stateService.save(state);
		return "redirect:/states";
	}
	
	@RequestMapping(value = "/states/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,State state) {
		stateService.update(state, id);
		return "redirect:/states";
	}
	
	@RequestMapping(value="/states/edit/{id}",method=RequestMethod.GET)
	public String editState(Model model,@PathVariable Integer id) {
		State state = stateService.findById(id);
		
		model.addAttribute("state",state);
		model.addAttribute("countries",countryService.getAll());
		return "parameters/state/stateEdit";
	}
	
	
	@RequestMapping(value = "/states/delete/{id}",method= {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Integer id) {
		stateService.delete(id);
		return "redirect:/states";
	}
	
	@RequestMapping(value="/states/details/{id}",method=RequestMethod.GET)
	public String detailState(Model model,@PathVariable Integer id) {
		State state = stateService.findById(id);
		
		model.addAttribute("state",state);
		return "parameters/state/stateDetails";
	}
	
}
