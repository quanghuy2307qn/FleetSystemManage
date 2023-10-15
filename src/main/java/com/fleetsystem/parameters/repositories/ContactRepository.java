package com.fleetsystem.parameters.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleetsystem.parameters.models.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
	@Query(value="select c from Contact c where c.email like %?1%")
	List<Contact> getByKeyword(String keyword);
	@Query(value="select c from Contact c where c.email like %?1%")
	Page<Contact> getPageByKeyWord(String keyword,Pageable pageable);
}
