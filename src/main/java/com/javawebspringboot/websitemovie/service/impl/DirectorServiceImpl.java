package com.javawebspringboot.websitemovie.service.impl;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.model.Director;
import com.javawebspringboot.websitemovie.repository.CountryRepository;
import com.javawebspringboot.websitemovie.repository.DirectorRepository;
import com.javawebspringboot.websitemovie.service.DirectorService;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	private DirectorRepository directorRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public void addNewDirector(String name, Integer idCountry) {
		long rowCount = directorRepository.count();
		String codeDirector = createCodeDirector(name, rowCount);
		Country country = countryRepository.findByIdCountry(idCountry);

		Director director = new Director(name, codeDirector, country, null);
		directorRepository.save(director);
	}

	private String createCodeDirector(String nameDirector, long rowCount) {
		String temp = Normalizer.normalize(nameDirector, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String codeDirector = pattern.matcher(temp).replaceAll("").toLowerCase();
		codeDirector = codeDirector.replace(" ", "-");
		codeDirector = codeDirector + "-" + (rowCount + 1);
		return codeDirector;
	}

	@Override
	public List<Director> findAllDerector() {
		return directorRepository.findAll();
	}

	@Override
	public List<Director> searchDirector(String keyWord) {
		
		return directorRepository.searchDirector(keyWord);
	}

}
