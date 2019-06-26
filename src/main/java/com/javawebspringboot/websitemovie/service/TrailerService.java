package com.javawebspringboot.websitemovie.service;

import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.websitemovie.model.Trailer;

public interface TrailerService {
	void addNewTrailer(String linkMovie, MultipartFile videoEpisode);

	Trailer findByLinkTrailer(String linkTrailer);
}
