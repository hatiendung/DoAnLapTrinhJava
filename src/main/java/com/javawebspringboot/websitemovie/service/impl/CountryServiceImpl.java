package com.javawebspringboot.websitemovie.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.repository.CountryRepository;
import com.javawebspringboot.websitemovie.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> findAllCountry() {
		return countryRepository.findAll();
	}

	@Override
	public Country findByIdCountry(int id) {
		return countryRepository.findByIdCountry(id);
	}

	@Override
	public Country findByCodeCountry(String codeCountry) {
		return countryRepository.findByCodeCountry(codeCountry);
	}

}
