package com.fleetsystem.parameters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fleetsystem.parameters.models.Contact;
import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.services.ContactService;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/contacts")
	public String getAll(Model model,String keyword) {
		List<Contact> listContacts;
		
		listContacts = keyword == null ? contactService.findAll() : contactService.findByKeyword(keyword);
		
		model.addAttribute("contacts",listContacts);
		return "parameters/contact/contactList";
				
				
	}
	
	
	@GetMapping("/contactAdd")
	public String addContact(Model model) {
		
		return "parameters/contact/contactAdd";
	}
	
	@PostMapping("/saveContact")
	public String saveContact(Contact contact) {
		contactService.save(contact);
		return "redirect:/contacts";
	}
	
	@RequestMapping(value="/contact/edit/{id}",method=RequestMethod.GET)
	public String editContact(Model model,@PathVariable Integer id) {
		Contact contact = contactService.findById(id);
		
		model.addAttribute("contact",contact);
		
		return "parameters/contact/contactEdit";
	}
	@RequestMapping(value = "/contact/update/{id}",method=RequestMethod.POST)
	public String updateContact(@PathVariable("id") Integer id,Contact contact) {
		contactService.update(contact, id);
		return "redirect:/contacts";
	}
	@RequestMapping(value = "/contact/delete/{id}",method= {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Integer id) {
		contactService.delete(id);
		return "redirect:/contacts";
	}
	@RequestMapping(value="/contact/details/{id}",method=RequestMethod.GET)
	public String detailState(Model model,@PathVariable Integer id) {
		Contact contact = contactService.findById(id);
		
		model.addAttribute("contact",contact);
		return "parameters/contact/contactDetails";
	}
}
