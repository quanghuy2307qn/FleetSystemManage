package com.fleetsystem.parameters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.models.Supplier;
import com.fleetsystem.parameters.repositories.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	//Get All Supplier	
	public List<Supplier> findAll(){
		return supplierRepository.findAll();
	}
	
	//Get Supplier by Id
	public Supplier findById(int id) {
		return supplierRepository.findById(id).orElse(null);
	}
	
	//Delete Supplier
	public void delete(Integer id) {
		supplierRepository.deleteById(id);
	}
	
	//Update Supplier
	public Supplier update(Supplier supplier,Integer id) {
		Supplier fromDb = supplierRepository.findById(id).orElse(null);
		if(fromDb == null) {
			return null;
		}
		
		fromDb.setAddress(supplier.getAddress());
		fromDb.setCity(supplier.getCity());
		fromDb.setEmail(supplier.getEmail());
		fromDb.setCountry(supplier.getCountry());
		fromDb.setDetails(supplier.getDetails());
		fromDb.setMobile(supplier.getMobile());
		fromDb.setName(supplier.getName());
		fromDb.setPhone(supplier.getPhone());
		fromDb.setState(supplier.getState());
		fromDb.setWebsite(supplier.getWebsite());
		return supplierRepository.save(fromDb);
	}
	
	//Save Supplier
	public void save(Supplier supplier) {
		supplierRepository.save(supplier);
	}
	
	public List<Supplier> findByKeyword(String keyword){
		return supplierRepository.getByKeyword(keyword);
	}
	public Page<Supplier> getPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return supplierRepository.findAll(pageable);
	}
	public Page<Supplier> findPageByKeyword(String keyword,int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return supplierRepository.getPageByKeyword(keyword, pageable);
	}
	public Page<Supplier> getAllWithSort(String field,String direction,int pageNumber){
		//asc or desc
		
		Sort sort = Sort.by(Sort.Direction.ASC.name());
		if(direction.equalsIgnoreCase(Sort.Direction.ASC.name())) {
			sort=Sort.by(field).ascending();
		}else {
			sort=Sort.by(field).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber-1,5,sort);
		return supplierRepository.findAll(pageable);
		
	}
}
