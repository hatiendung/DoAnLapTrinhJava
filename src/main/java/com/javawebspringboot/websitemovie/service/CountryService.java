package com.javawebspringboot.websitemovie.service;

import java.util.List;

import com.javawebspringboot.websitemovie.model.Country;

public interface CountryService {

	List<Country> findAllCountry();
	
	Country findByIdCountry(int id);
	
	Country findByCodeCountry(String codeCountry);
}
