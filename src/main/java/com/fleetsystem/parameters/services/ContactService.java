package com.fleetsystem.parameters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetsystem.parameters.models.Contact;
import com.fleetsystem.parameters.models.Location;
import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.repositories.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;
	
	//Get All Contact
		public List<Contact> findAll(){
			return contactRepository.findAll();
		}
		
		//Get Contact by Id
		public Contact findById(int id) {
			return contactRepository.findById(id).orElse(null);
		}
		
		//Delete Contact
		public void delete(Integer id) {
			contactRepository.deleteById(id);
		}
		
		//Update State
		public Contact update(Contact contact,Integer id) {
			Contact fromDb = contactRepository.findById(id).orElse(null);
			if(fromDb == null) {
				return null;
			}
			
			fromDb.setFirstname(contact.getFirstname());
			fromDb.setLastname(contact.getLastname());
			fromDb.setEmail(contact.getEmail());
			fromDb.setMobile(contact.getMobile());
			fromDb.setPhone(contact.getPhone());
			fromDb.setRemarks(contact.getRemarks());
			return contactRepository.save(fromDb);
		}
		
		//Save State
		public void save(Contact contact) {
			contactRepository.save(contact);
		}
		
		public List<Contact> findByKeyword(String keyword){
			return contactRepository.getByKeyword(keyword);
		}
		
		
}
