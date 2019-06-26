package com.javawebspringboot.websitemovie.service.impl;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Actor;
import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.repository.ActorRepository;
import com.javawebspringboot.websitemovie.repository.CountryRepository;
import com.javawebspringboot.websitemovie.service.ActorService;

@Service
@Transactional
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Actor findActorByCodeActor(String codeActor) {
		return actorRepository.findActorByCodeActor(codeActor);
	}

	@Override
	public List<Actor> searchActor(String nameActor) {

		return actorRepository.searchActor(nameActor);
	}

	@Override
	public void addNewActor(String name, Integer idCountry) {

		long rowCount = actorRepository.count();
		String codeActor = createCodeActor(name, rowCount);
		Country country = countryRepository.findByIdCountry(idCountry);
		
		Actor actor = new Actor(name, country, codeActor, null);
		actorRepository.save(actor);
	}

	private String createCodeActor(String nameActor, long rowCount) {
		String temp = Normalizer.normalize(nameActor, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String codeActor = pattern.matcher(temp).replaceAll("").toLowerCase();
		codeActor = codeActor.replace(" ", "-");
		codeActor = codeActor + "-" + (rowCount + 1);
		return codeActor;
	}

	@Override
	public List<Actor> findAllActor() {
		return actorRepository.findAll();
	}

	

}
