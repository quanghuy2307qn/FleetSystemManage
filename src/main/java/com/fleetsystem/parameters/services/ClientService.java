package com.fleetsystem.parameters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.models.Contact;
import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.models.Location;
import com.fleetsystem.parameters.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
			//Get All Client	
			public List<Client> findAll(){
				return clientRepository.findAll();
			}
			
			//Get Client by Id
			public Client findById(int id) {
				return clientRepository.findById(id).orElse(null);
			}
			
			//Delete Client
			public void delete(Integer id) {
				clientRepository.deleteById(id);
			}
			
			//Update Client
			public Client update(Client client,Integer id) {
				Client fromDb = clientRepository.findById(id).orElse(null);
				if(fromDb == null) {
					return null;
				}
				
				fromDb.setAddress(client.getAddress());
				fromDb.setCity(client.getCity());
				fromDb.setEmail(client.getEmail());
				fromDb.setCountry(client.getCountry());
				fromDb.setDetails(client.getDetails());
				fromDb.setMobile(client.getMobile());
				fromDb.setName(client.getName());
				fromDb.setPhone(client.getPhone());
				fromDb.setState(client.getState());
				fromDb.setWebsite(client.getWebsite());
				return clientRepository.save(fromDb);
			}
			
			//Save Client
			public void save(Client client) {
				clientRepository.save(client);
			}
			
			public List<Client> findByKeyword(String keyword){
				return clientRepository.getByKeyword(keyword);
			}
			
			public Page<Client> getPage(int pageNumber){
				Pageable pageable = PageRequest.of(pageNumber-1, 5);
				return clientRepository.findAll(pageable);
			}
			public Page<Client> findPageByKeyword(String keyword,int pageNumber){
				Pageable pageable = PageRequest.of(pageNumber-1, 5);
				return clientRepository.getPageByKeyword(keyword, pageable);
			}
			public Page<Client> getAllWithSort(String field,String direction,int pageNumber){
				//asc or desc
				
				Sort sort = Sort.by(Sort.Direction.ASC.name());
				if(direction.equalsIgnoreCase(Sort.Direction.ASC.name())) {
					sort=Sort.by(field).ascending();
				}else {
					sort=Sort.by(field).descending();
				}
				Pageable pageable = PageRequest.of(pageNumber-1,5,sort);
				return clientRepository.findAll(pageable);
				
			}
			
}
