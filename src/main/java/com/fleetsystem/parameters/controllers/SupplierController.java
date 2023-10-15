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

import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.models.Supplier;
import com.fleetsystem.parameters.services.CountryService;
import com.fleetsystem.parameters.services.StateService;
import com.fleetsystem.parameters.services.SupplierService;

@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private StateService stateService;
	@Autowired
	private CountryService countryService;
	
	//@GetMapping("/suppliers")
	//public String getAll(Model model,String keyword) {
		//List<Supplier> listSupplier;
		//listSupplier= keyword == null ? supplierService.findAll() : supplierService.findByKeyword(keyword);
		//model.addAttribute("suppliers",listSupplier);
		
		//return "parameters/supplierList";
	//}
	
	@GetMapping("/suppliers/page/{pageNumber}")
	public String getOnePage(Model model,@PathVariable("pageNumber") int currentPage,String keyword) {
		Page<Supplier> page = supplierService.getPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Supplier> suppliers;
		if(keyword==null) {
			suppliers = page.getContent();
		}else {
			page=supplierService.findPageByKeyword(keyword, currentPage);
			suppliers=page.getContent();
		}
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		model.addAttribute("suppliers",suppliers);
		
		return "/parameters/supplier/supplierList";
		
	}
	
	@GetMapping("/suppliers")
	public String getAllPages(Model model,String keyword) {
		return getOnePage(model,1,keyword);
	}
	
	@GetMapping("/suppliers/page/{pageNumber}/{field}")
	public String getPageWithSort(Model model, @PathVariable("field") String field,
			@RequestParam(value="sortDir",defaultValue = "ASC") String sortDir,
			@PathVariable("pageNumber") int currentPage){
		
		Page<Supplier> page;
		page = supplierService.getAllWithSort(field, sortDir,currentPage);
		List<Supplier> suppliers = page.getContent();
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("totalItems",totalItems);
		
		
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc" : "asc" ); 
		
		model.addAttribute("suppliers",suppliers);
		return "parameters/supplier/supplierList";
	}
	
	@GetMapping("/supplierAdd")
	public String addSupplier(Model model) {
		
		model.addAttribute("countries",countryService.getAll());
		return "parameters/supplier/supplierAdd";
	}
	
	@PostMapping("/supplierSave")
	public String saveSupplier(Supplier supplier) {
		supplierService.save(supplier);
		return "redirect:/suppliers";
	}
	
	@RequestMapping(value="/supplier/edit/{id}",method=RequestMethod.GET)
	public String editSupplier(Model model,@PathVariable Integer id) {
		Supplier supplier = supplierService.findById(id);
		model.addAttribute("supplier",supplier);
		
		model.addAttribute("countries",countryService.getAll());
		return "parameters/supplier/supplierEdit";
	}
	
	@RequestMapping(value = "/supplier/update/{id}",method=RequestMethod.POST)
	public String updateSupplier(@PathVariable("id") Integer id,Supplier supplier) {
		supplierService.update(supplier, id);
		return "redirect:/suppliers";
	}
	
	@RequestMapping(value = "/supplier/delete/{id}",method= {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteSupplier(@PathVariable Integer id) {
		supplierService.delete(id);
		return "redirect:/suppliers";
	}
	
	@RequestMapping(value="/supplier/detail/{id}",method=RequestMethod.GET)
	public String detailSupplier(Model model,@PathVariable Integer id) {
		Supplier supplier = supplierService.findById(id);
		//model.addAttribute("states",stateService.findAll());
		//model.addAttribute("countries",countryService.getAll());
		model.addAttribute("supplier",supplier);
		return "parameters/supplier/supplierDetails";
	}
}

