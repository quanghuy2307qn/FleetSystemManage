package com.fleetsystem.parameters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.models.Supplier;
import com.fleetsystem.parameters.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	//Get All States
	public List<State> findAll(){
		return stateRepository.findAll();
	}
	
	//Get State by Id
	public State findById(int id) {
		return stateRepository.findById(id).orElse(null);
	}
	
	//Delete State 
	public void delete(Integer id) {
		stateRepository.deleteById(id);
	}
	
	//Update State
	public State update(State state,Integer id) {
		State fromDb = stateRepository.findById(id).orElse(null);
		if(fromDb == null) {
			return null;
		}
		
		fromDb.setCapital(state.getCapital());
		fromDb.setCode(state.getCode());
		fromDb.setDetails(state.getDetails());
		fromDb.setName(state.getName());
		return stateRepository.save(fromDb);
	}
	
	//Save State
	public void save(State state) {
		stateRepository.save(state);
	}
	
	public List<State> findByKeyword(String keyword){
		return stateRepository.getByKeyword(keyword);
	}
	
	public Page<State> getPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return stateRepository.findAll(pageable);
	}
	public Page<State> findPageByKeyword(String keyword,int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return stateRepository.getPageByKeyword(keyword, pageable);
	}
	public Page<State> getAllWithSort(String field,String direction,int pageNumber){
		//asc or desc
		
		Sort sort = Sort.by(Sort.Direction.ASC.name());
		if(direction.equalsIgnoreCase(Sort.Direction.ASC.name())) {
			sort=Sort.by(field).ascending();
		}else {
			sort=Sort.by(field).descending();
		}
		Pageable pageable = PageRequest.of(pageNumber-1,5,sort);
		return stateRepository.findAll(pageable);
		
	}

}
