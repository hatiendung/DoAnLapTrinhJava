package com.javawebspringboot.websitemovie.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Trailer;

@Repository
@Transactional
public interface TrailerRepository extends JpaRepository<Trailer, Integer> {

	List<Trailer> findByMovie(Movie movie);

	Trailer findByLinkTrailer(String linkTrailer);
}
