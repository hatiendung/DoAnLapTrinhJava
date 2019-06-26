package com.javawebspringboot.websitemovie.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.model.Movie;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	//Movie findTop1ByOrderByIdMovieDesc();

	Page<Movie> findByCountry(Country country, Pageable pageable);

	List<Movie> findAllByOrderByDatetimePostDesc();

	List<Movie> findTop10ByStatusOrderByDatetimePostDesc(int status);

	List<Movie> findTop16ByStatusOrderByDatetimePostDesc(int status);

	List<Movie> findByCountry(Country country);

	Movie findByLinkMovie(String linkMovie);

	void deleteByIdMovie(Integer idMovie);

	Movie findByIdMovie(Integer idMovie);

	List<Movie> findTop12ByOrderByViewDesc();

	@Query("SELECT m FROM Movie m where m.nameMovie LIKE %?1% ORDER BY datetimePost DESC")
	List<Movie> searchMovie(String keyWord);

	long count();

	List<Movie> findByYearProduce(Integer year);
	
	
}
