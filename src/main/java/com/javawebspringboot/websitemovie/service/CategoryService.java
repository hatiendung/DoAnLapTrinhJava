package com.javawebspringboot.websitemovie.service;

import java.util.List;

import com.javawebspringboot.websitemovie.model.Category;

public interface CategoryService {

	List<Category> findAllCategory();
	
	Category findByCodeCategory(String codeCategory);
}
