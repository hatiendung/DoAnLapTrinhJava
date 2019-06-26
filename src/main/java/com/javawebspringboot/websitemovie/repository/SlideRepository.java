package com.javawebspringboot.websitemovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Slide;

@Repository
@Transactional
public interface SlideRepository extends JpaRepository<Slide, Integer> {
	
	List<Slide> findByStatus(Integer status);
	
	Slide findByIdSlide(Integer idSlide);

	
	void deleteByIdSlide(Integer idSlide);

	Slide findByMovie(Movie movieUpdate);
}
