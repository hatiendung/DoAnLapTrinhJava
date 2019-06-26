package com.javawebspringboot.websitemovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.websitemovie.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	Country findByIdCountry(int id);
	
	Country findByCodeCountry(String codeCountry);
}
