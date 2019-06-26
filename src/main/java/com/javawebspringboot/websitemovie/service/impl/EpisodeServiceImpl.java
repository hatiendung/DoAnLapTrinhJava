package com.javawebspringboot.websitemovie.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.websitemovie.model.Episode;
import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.repository.EpisodeRepository;
import com.javawebspringboot.websitemovie.repository.MovieRepository;
import com.javawebspringboot.websitemovie.service.EpisodeService;

@Service
@Transactional
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
	private EpisodeRepository episodeSeriesRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Episode findByLinkEpisode(String linkEpisode) {

		return episodeSeriesRepository.findByLinkEpisode(linkEpisode);
	}

	@Override
	public void addNewEpisode(String linkMovie, MultipartFile videoEpisode) {
		Movie movie = movieRepository.findByLinkMovie(linkMovie);
		long countRow = episodeSeriesRepository.count() + 1;

		List<Episode> movieEpisode = episodeSeriesRepository.findByMovie(movie);
		Episode newEpisode = new Episode();
		String linkEpisode = "";
		if (movieEpisode.size() > 0) {
			// da ton tai it nhat 1 tap phim
			// can stt cua tap phim
			int size = movieEpisode.size();
			newEpisode.setOrdinalNumbers(movieEpisode.get(size - 1).getOrdinalNumbers());
			linkEpisode = getLinkEpisode(movie.getLinkMovie(), countRow,
					movieEpisode.get(size - 1).getOrdinalNumbers());
		} else {
			// chua co tap phim
			// mac dinh la tap 1
			newEpisode.setOrdinalNumbers(0);
			linkEpisode = getLinkEpisode(movie.getLinkMovie(), countRow);
		}

		boolean result = saveEpisodeToDisk(linkEpisode, videoEpisode);
		if (result == true) {
			newEpisode.setLinkEpisode(linkEpisode);
			newEpisode.setMovie(movie);
			newEpisode.setOrdinalNumbers(newEpisode.getOrdinalNumbers() + 1);
			episodeSeriesRepository.save(newEpisode);

		} else {
			System.out.println("Khong luu duoc tap phim vao may tinh");
		}

	}

	private boolean saveEpisodeToDisk(String linkEpisode, MultipartFile videoEpisode) {
		String UPLOAD_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static/video/movie/";
		try {
			byte[] bytes = videoEpisode.getBytes();

			Path path = Paths.get(UPLOAD_FOLDER + "" + linkEpisode + ".mp4");
			Files.write(path, bytes);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private String getLinkEpisode(String linkMovie, long maxIdEpisode) {
		return linkMovie + "-tap-1-" + (maxIdEpisode + 1);
	}

	private String getLinkEpisode(String linkMovie, long countRow, Integer ordinalNumbers) {
		String linkEpisode = linkMovie + "-tap-" + (ordinalNumbers + 1) + "-" + countRow;

		return linkEpisode;
	}

	@Override
	public void downloadMovie(String linkEpisode, HttpServletResponse response) {

		try {
			File file = ResourceUtils.getFile("classpath:static/video/movie/" + linkEpisode + ".mp4");

			byte[] data = FileUtils.readFileToByteArray(file);
			// Thiết lập thông tin trả về
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
			response.setContentLength(data.length);
			InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void downloadTrailer(String linkTrailer, HttpServletResponse response) {

		try {
			File file = ResourceUtils.getFile("classpath:static/video/trailer/" + linkTrailer + ".mp4");

			byte[] data = FileUtils.readFileToByteArray(file);
			// Thiết lập thông tin trả về
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
			response.setContentLength(data.length);
			InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public long countEpisode() {
		return episodeSeriesRepository.count();
	}

	@Override
	public void deleteEpisode(String linkEpisode) {
		episodeSeriesRepository.deleteByLinkEpisode(linkEpisode);
		String UPLOAD_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static/video/movie/";
		File fileDelete = new File(UPLOAD_FOLDER + linkEpisode + ".mp4");
		fileDelete.delete();

	}

}
