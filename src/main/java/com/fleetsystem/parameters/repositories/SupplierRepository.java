package com.fleetsystem.parameters.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleetsystem.parameters.models.Client;
import com.fleetsystem.parameters.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	@Query(value="select s from Supplier s where s.name LIKE %?1% or s.address LIKE %?1%")
	List<Supplier> getByKeyword(String keyword);
	
	@Query(value="select s from Supplier s where s.name LIKE %?1% or s.address LIKE %?1%")
	Page<Supplier> getPageByKeyword(String keyword,Pageable pageable);
}
