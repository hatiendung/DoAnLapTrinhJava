package com.javawebspringboot.websitemovie.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javawebspringboot.websitemovie.model.Category;
import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.model.Movie;

public interface MovieService {

	List<Movie> findAllMovie();

	void saveMovie(Movie movie, int countryId, List<Integer> listCategory);

	List<Movie> findTop10MovieComingSoon();

	List<Movie> findTop16MovieNewUpdate();

	List<Movie> findByCountry(Country country);

	Movie findByLinkMovie(String linkMovie);

	void deleteMovie(int idMovie);

	Page<Movie> findAllMovieByContry(Country country, Pageable pageable);

	void updateMovie(Movie movie);

	List<Movie> findTop12ByOrderByViewDesc();
	
	List<Movie> findTop10ByOrderByViewDesc();

	List<Movie> searchMovie(String keyWord);

	long countMovie();

	void sortEpisode(Movie movie);

	List<Movie> findByYearProduce(Integer year);

	Page<Movie> findAllMovieByCategory(Category category, Pageable pageable);

	Page<Movie> findAllMovieByYearProduce(Integer year, Pageable pageable);

	void getDataChart(List<String> label, List<Integer> point, List<Movie> listMovie);

	Page<Movie> findAllMovie(Pageable pageable);

	void updateEnableOrDisableMovie(Integer idMovie);

	List<Movie> findAllByEnableOrderByDatetimePost();

}
