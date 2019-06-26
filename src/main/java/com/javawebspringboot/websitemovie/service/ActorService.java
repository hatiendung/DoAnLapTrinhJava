package com.javawebspringboot.websitemovie.service;

import java.util.List;

import com.javawebspringboot.websitemovie.model.Actor;

public interface ActorService {
	Actor findActorByCodeActor(String codeActor);

	List<Actor> searchActor(String nameActor);

	List<Actor> findAllActor();

	void addNewActor(String name, Integer idCountry);

}
