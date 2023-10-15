package com.fleetsystem.parameters.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.models.Location;
import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.services.ClientService;
import com.fleetsystem.parameters.services.CountryService;
import com.fleetsystem.parameters.services.StateService;

@Controller
public class ClientController {
	@Autowired
	private ClientService clientService;
	@Autowired
	private StateService stateService;
	@Autowired
	private CountryService countryService;
	
	//@GetMapping("/clients")
	//public String getAll(Model model,String keyword) {
	//	List<Client> listClient;
		//listClient= keyword==null ? clientService.findAll() : clientService.findByKeyword(keyword);
		//model.addAttribute("clients",listClient);
		
		//return "parameters/clientList";
	//}
	
	@GetMapping("/clients/page/{pageNumber}")
	public String getOnePage(Model model,@PathVariable("pageNumber") int currentPage,String keyword) {
		Page<Client> page = clientService.getPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Client> clients;
		if(keyword==null) {
			clients = page.getContent();
		}else {
			page=clientService.findPageByKeyword(keyword, currentPage);
			clients=page.getContent();
		}
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		model.addAttribute("clients",clients);
		
		return "/parameters/client/clientList";
		
	}
	
	@GetMapping("/clients")
	public String getAllPages(Model model,String keyword) {
		return getOnePage(model,1,keyword);
	}
	
	@GetMapping("/clients/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model, @PathVariable("field") String field,
			@RequestParam(value="sortDir",defaultValue = "ASC") String sortDir,
			@PathVariable("pageNumber") int currentPage){
		
		Page<Client> page;
		page = clientService.getAllWithSort(field, sortDir,currentPage);
		List<Client> clients = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		
		
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc" : "asc" ); 
		
		model.addAttribute("clients",clients);
		return "parameters/client/clientList";
	}
	
	
	@GetMapping("/clientAdd")
	public String addClient(Model model) {
		
		model.addAttribute("countries",countryService.getAll());
		return "parameters/client/clientAdd";
	}
	
	@PostMapping("/clientSave")
	public String saveClient(Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}
	
	@RequestMapping(value="/client/edit/{id}",method=RequestMethod.GET)
	public String editClient(Model model,@PathVariable Integer id) {
		Client client = clientService.findById(id);
		model.addAttribute("client",client);
		
		model.addAttribute("countries",countryService.getAll());
		return "parameters/client/clientEdit";
	}
	
	@RequestMapping(value = "/client/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,Client client) {
		clientService.update(client, id);
		return "redirect:/clients";
	}
	
	@RequestMapping(value = "/client/delete/{id}",method= {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteClient(@PathVariable Integer id) {
		clientService.delete(id);
		return "redirect:/clients";
	}
	
	@RequestMapping(value="/client/detail/{id}",method=RequestMethod.GET)
	public String detailClient(Model model,@PathVariable Integer id) {
		Client client = clientService.findById(id);
		model.addAttribute("states",stateService.findAll());
		model.addAttribute("countries",countryService.getAll());
		model.addAttribute("client",client);
		return "parameters/client/clientDetails";
	}
	
}


