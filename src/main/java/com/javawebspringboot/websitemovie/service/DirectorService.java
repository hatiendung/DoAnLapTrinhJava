package com.javawebspringboot.websitemovie.service;

import java.util.List;

import com.javawebspringboot.websitemovie.model.Director;

public interface DirectorService {

	void addNewDirector(String name, Integer idCountry);

	List<Director> findAllDerector();

	List<Director> searchDirector(String keyWord);

}
