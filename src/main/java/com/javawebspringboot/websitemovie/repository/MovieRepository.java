package com.javawebspringboot.websitemovie.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Category;
import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Slide;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Page<Movie> findByCountryAndEnable(Country country, int enable, Pageable pageable);

	Page<Movie> findByCategoryListAndEnable(List<Category> categoryList, int enable, Pageable pageable);

	List<Movie> findByEnableOrderByDatetimePostDesc(int enable);

	Page<Movie> findByEnableOrderByDatetimePostDesc(int enable, Pageable pageable);

	List<Movie> findTop10ByStatusAndEnableOrderByDatetimePostDesc(int status, int enable);

	List<Movie> findTop16ByStatusAndEnableOrderByDatetimePostDesc(int status, int enable);

	List<Movie> findByCountryAndEnable(Country country, int enable);

	Movie findByLinkMovie(String linkMovie);

	void deleteByIdMovie(Integer idMovie);

	Movie findByIdMovie(Integer idMovie);

	List<Movie> findTop12ByEnableOrderByViewDesc(int enable);

	List<Movie> findTop10ByEnableOrderByViewDesc(int enable);

	@Query("SELECT m FROM Movie m where m.nameMovie LIKE %?1% ORDER BY datetimePost DESC")
	List<Movie> searchMovie(String keyWord);

	long count();

	List<Movie> findByYearProduceAndEnable(Integer year, int enable);

	Page<Movie> findByYearProduceAndEnable(Integer year, int enable, Pageable pageable);

	Movie findBySlide(Slide slide);


	Page<Movie> findAllByOrderByDatetimePostDesc(Pageable pageable);

	List<Movie> findAllByEnableOrderByDatetimePost(int enable);

	
}
