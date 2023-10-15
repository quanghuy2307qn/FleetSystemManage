package com.fleetsystem.parameters.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleetsystem.parameters.models.Country;
import com.fleetsystem.parameters.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	@Query(value="select l from Location l where l.city LIKE %?1% or l.address LIKE %?1%")
	List<Location> getByKeyword(String keyword);
	
	@Query(value="select l from Location l where l.city LIKE %?1% or l.address LIKE %?1%")
	Page<Location> getPageByKeyword(String keyword,Pageable pageable);
}
