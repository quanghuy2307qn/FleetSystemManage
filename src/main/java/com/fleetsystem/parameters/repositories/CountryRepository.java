package com.fleetsystem.parameters.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleetsystem.parameters.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	@Query(value="select c from Country c where c.description LIKE %?1%")
	List<Country> getByKeyword(String keyword);
	
	@Query(value="select c from Country c where c.description LIKE %?1%")
	Page<Country> getPageByKeyword(String keyword,Pageable pageable);
}
