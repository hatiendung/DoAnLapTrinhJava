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
public class CategoryServiceImpl implements CategoryService {

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

	@Override
	public void getDataChart(List<String> lbCategory, List<Float> pointCategory, List<Category> listCategorieList) {
		float sum = 0;
		for (Category category2 : listCategorieList) {
			sum += category2.getMovieList().size();
		}
		for (Category category : listCategorieList) {
			float size = category.getMovieList().size();
			float persent = (size / sum)*100;
		
			lbCategory.add(category.getNameCategory());
			pointCategory.add(persent);
		}
	}

}
