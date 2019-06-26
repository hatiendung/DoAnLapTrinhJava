package com.javawebspringboot.websitemovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByCodeCategory(String codeCategory);

	Category findByIdCategory(Integer idCategory);
}
