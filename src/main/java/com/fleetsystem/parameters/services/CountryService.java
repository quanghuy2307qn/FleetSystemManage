package com.fleetsystem.parameters.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.repositories.CountryRepository;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getAll(){
		return countryRepository.findAll();
	}
	
	public Page<Country> getPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return countryRepository.findAll(pageable);
	}
	public List<Country> findByKeyword(String keyword){
		return countryRepository.getByKeyword(keyword);
	}
	
	public Page<Country> findPageByKeyword(String keyword,int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return countryRepository.getPageByKeyword(keyword, pageable);
	}
	
	public void save(Country country) {
		countryRepository.save(country);
	}
	
	public void delete(Integer id) {
		countryRepository.deleteById(id);
	}
	
	public Country update(Country country,Integer id) {
		Country fromDb = countryRepository.findById(id).orElse(null);
		if(fromDb == null) {
			return null;
		}
		fromDb.setCapital(country.getCapital());
		fromDb.setCode(country.getCode());
		fromDb.setContinent(country.getContinent());
		fromDb.setDescription(country.getDescription());
		fromDb.setNationality(country.getNationality());
		return countryRepository.save(fromDb);
		
	}
	public Country getById(Integer id) {
		return countryRepository.findById(id).orElse(null);
	}
	
	
	public Page<Country> getAllWithSort(String field,String direction,int pageNumber){
		//asc or desc
		
		Sort sort = Sort.by(Sort.Direction.ASC.name());
		if(direction.equalsIgnoreCase(Sort.Direction.ASC.name())) {
			sort=Sort.by(field).ascending();
		}else {
			sort=Sort.by(field).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber-1,5,sort);
		return countryRepository.findAll(pageable);
		
	}
}
