package com.fleetsystem.parameters.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleetsystem.parameters.models.State;
import com.fleetsystem.parameters.models.Supplier;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
	@Query(value="select s from State s where s.name LIKE %?1% or s.capital LIKE %?1%")
	List<State> getByKeyword(String keyword);
	
	@Query(value="select s from State s where s.name LIKE %?1% or s.capital LIKE %?1%")
	Page<State> getPageByKeyword(String keyword,Pageable pageable);
}
