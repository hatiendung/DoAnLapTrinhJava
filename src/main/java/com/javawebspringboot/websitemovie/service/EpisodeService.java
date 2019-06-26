package com.javawebspringboot.websitemovie.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.websitemovie.model.Episode;

public interface EpisodeService {
	Episode findByLinkEpisode(String linkEpisode);

	void addNewEpisode(String linkMovie, MultipartFile videoEpisode);

	void downloadMovie(String linkEpisode, HttpServletResponse response);

	long countEpisode();

	void deleteEpisode(String linkEpisode);

	void downloadTrailer(String linkTrailer, HttpServletResponse response);
}
