package com.javawebspringboot.websitemovie.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Trailer;
import com.javawebspringboot.websitemovie.repository.MovieRepository;
import com.javawebspringboot.websitemovie.repository.TrailerRepository;
import com.javawebspringboot.websitemovie.service.TrailerService;

@Service
@Transactional
public class TrailerServiceImpl implements TrailerService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TrailerRepository trailerRepository;

	@Override
	public void addNewTrailer(String linkMovie, MultipartFile videoEpisode) {

		Movie movie = movieRepository.findByLinkMovie(linkMovie);
		long countRow = trailerRepository.count() + 1;

		List<Trailer> movieTrailer = trailerRepository.findByMovie(movie);
		Trailer newTrailer = new Trailer();
		String linkTrailer = "";
		if (movieTrailer.size() > 0) {
			// da ton tai it nhat 1 tap phim
			// can stt cua tap phim
			int size = movieTrailer.size();
			newTrailer.setOrdinalNumbers(movieTrailer.get(size - 1).getOrdinalNumbers());
			linkTrailer = getLinkTrailer(movie.getLinkMovie(), countRow,
					movieTrailer.get(size - 1).getOrdinalNumbers());
		} else {
			// chua co tap phim
			// mac dinh la tap 1
			newTrailer.setOrdinalNumbers(0);
			linkTrailer = getLinkTrailer(movie.getLinkMovie(), countRow);
		}

		boolean result = saveTrailerToDisk(linkTrailer, videoEpisode);
		if (result == true) {
			newTrailer.setLinkTrailer(linkTrailer);
			newTrailer.setMovie(movie);
			newTrailer.setOrdinalNumbers(newTrailer.getOrdinalNumbers() + 1);
			trailerRepository.save(newTrailer);

		} else {
			System.out.println("Khong luu duoc tap phim vao may tinh");
		}
	}

	private boolean saveTrailerToDisk(String linkTrailer, MultipartFile videoEpisode) {
		String UPLOAD_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static/video/trailer/";
		try {
			byte[] bytes = videoEpisode.getBytes();

			Path path = Paths.get(UPLOAD_FOLDER + "" + linkTrailer + ".mp4");
			Files.write(path, bytes);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private String getLinkTrailer(String linkMovie, long countRow) {
		return linkMovie + "-trailer-1-" + (countRow + 1);
	}

	private String getLinkTrailer(String linkMovie, long countRow, Integer ordinalNumbers) {
		String linkTrailer = linkMovie + "-trailer-" + (ordinalNumbers + 1) + "-" + countRow;

		return linkTrailer;
	}

	@Override
	public Trailer findByLinkTrailer(String linkTrailer) {
		return trailerRepository.findByLinkTrailer(linkTrailer);
	}

}
