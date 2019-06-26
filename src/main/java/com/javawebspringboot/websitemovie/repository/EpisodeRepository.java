package com.javawebspringboot.websitemovie.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.websitemovie.model.Episode;
import com.javawebspringboot.websitemovie.model.Movie;

@Repository
@Transactional
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
	Episode findByLinkEpisode(String linkEpisode);

	//Episode findTop1ByOrderByIdEpisodeDesc();

	List<Episode> findByMovie(Movie movie);
	
	void deleteByLinkEpisode(String linkEpisode);
	
	long count();
}
