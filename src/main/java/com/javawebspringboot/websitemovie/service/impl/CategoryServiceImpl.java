package com.javawebspringboot.websitemovie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Category;
import com.javawebspringboot.websitemovie.repository.CategoryRepository;
import com.javawebspringboot.websitemovie.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findByCodeCategory(String codeCategory) {
		return categoryRepository.findByCodeCategory(codeCategory);
	}

}
