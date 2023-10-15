package com.fleetsystem.parameters.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.models.Location;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query(value="select c from Client c where c.name LIKE %?1% or c.address LIKE %?1%")
	List<Client> getByKeyword(String keyword);
	
	@Query(value="select c from Client c where c.name LIKE %?1% or c.address LIKE %?1%")
	Page<Client> getPageByKeyword(String keyword,Pageable pageable);
}
