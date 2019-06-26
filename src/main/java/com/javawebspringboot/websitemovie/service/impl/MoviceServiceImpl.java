package com.javawebspringboot.websitemovie.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.websitemovie.model.Category;
import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.model.Episode;
import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Slide;
import com.javawebspringboot.websitemovie.model.User;
import com.javawebspringboot.websitemovie.repository.CategoryRepository;
import com.javawebspringboot.websitemovie.repository.CountryRepository;
import com.javawebspringboot.websitemovie.repository.MovieRepository;
import com.javawebspringboot.websitemovie.repository.SlideRepository;
import com.javawebspringboot.websitemovie.repository.UserRepository;
import com.javawebspringboot.websitemovie.service.MovieService;

@Service
@Transactional
public class MoviceServiceImpl implements MovieService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SlideRepository slideRepository;

	@Override
	public List<Movie> findAllMovie() {
		return movieRepository.findAllByOrderByDatetimePostDesc();
	}

	@Override
	public void saveMovie(Movie movie, int countryId, List<Integer> listCategory) {
		MultipartFile avatar = movie.getAvatar();
		MultipartFile slideImg = movie.getSlideImg();
		// lay ra max id trong bang movie
		long rowCount = movieRepository.count();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User userPost = userRepository.findByEmail(userDetails.getUsername());
		String linkMovie = createLinkMovie(rowCount, movie.getNameMovie());
		movie.setLinkMovie(linkMovie);

		List<Category> categories = new ArrayList<Category>();
		for (Integer idCategory : listCategory) {
			categories.add(categoryRepository.findByIdCategory(idCategory));
		}
		saveImageAvartarToDisk(avatar, linkMovie);

		movie.setUserPost(userPost);
		movie.setDatetimePost(LocalDateTime.now());
		movie.setCategoryList(categories);
		movie.setView(0);
		movie.setCountry(countryRepository.findByIdCountry(countryId));

		movieRepository.save(movie);

		if (!slideImg.getOriginalFilename().equals("")) {
			saveSlide(slideImg, linkMovie, movie);

		}

	}

	private void saveSlide(MultipartFile slideImg, String linkMovie, Movie movie) {

		String linkSlide = saveImageSlideToDish(slideImg, linkMovie);
		if (!linkSlide.equals("")) {
			Slide slide = new Slide(linkSlide, movie, 1);
			slideRepository.save(slide);
		}
	}

	private String saveImageSlideToDish(MultipartFile slideImg, String linkMovie) {

		String UPLOAD_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static/images/slide/";
		try {
			byte[] bytes = slideImg.getBytes();
			String linkSlide = "";
			linkSlide = "slide-" + linkMovie;
			Path path = Paths.get(UPLOAD_FOLDER + "" + linkSlide + ".jpg");
			Files.write(path, bytes);
			return linkSlide;
		} catch (IOException e) {
			return null;
		}
	}

	private void saveImageAvartarToDisk(MultipartFile avatar, String linkMovie) {
		String UPLOAD_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static/images/movie/";
		try {
			byte[] bytes = avatar.getBytes();
			linkMovie += ".jpg";
			Path path = Paths.get(UPLOAD_FOLDER + "" + linkMovie);
			Files.write(path, bytes);
		} catch (IOException e) {
		}
	}

	private String createLinkMovie(long maxId, String nameMovie) {
		String temp = Normalizer.normalize(nameMovie, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String linkMovie = pattern.matcher(temp).replaceAll("").toLowerCase();
		linkMovie = linkMovie.replace(" ", "-");
		linkMovie = linkMovie + "-" + (maxId + 1);
		return linkMovie;
	}

	@Override
	public List<Movie> findByCountry(Country country) {
		return movieRepository.findByCountry(country);
	}

	@Override
	public Movie findByLinkMovie(String linkMovie) {
		return movieRepository.findByLinkMovie(linkMovie);
	}

	@Override
	public List<Movie> findTop10MovieComingSoon() {
		return movieRepository.findTop10ByStatusOrderByDatetimePostDesc(0);
	}

	@Override
	public List<Movie> findTop16MovieNewUpdate() {
		// TODO Auto-generated method stub
		return movieRepository.findTop16ByStatusOrderByDatetimePostDesc(1);
	}

	@Override
	public void deleteMovie(int idMovie) {
		Movie movie = movieRepository.findByIdMovie(idMovie);
		String linkMovie = movie.getLinkMovie();
		// delete trong csdl
		movieRepository.deleteByIdMovie(idMovie);

		// delete anh trong project

		deleteImageMovie(linkMovie);

	}

	private void deleteImageMovie(String linkMovie) {
		String DELETE_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static/images/movie/";
		linkMovie += ".jpg";
		File fileDelete = new File(DELETE_FOLDER + linkMovie);
		if (fileDelete.delete()) {
			System.out.println("delete thanh cong");
		} else {
			System.out.println("delete ko thanh cong");
		}

	}

	@Override
	public Page<Movie> findAllMovieByContry(Country country, Pageable pageable) {
		return movieRepository.findByCountry(country, pageable);
	}

	@Override
	public void updateMovie(Movie movie) {

		movie.setView(movie.getView() + 1);
		movieRepository.save(movie);

	}

	@Override
	public List<Movie> findTop12ByOrderByViewDesc() {
		return movieRepository.findTop12ByOrderByViewDesc();
	}

	@Override
	public List<Movie> searchMovie(String keyWord) {

		return movieRepository.searchMovie(keyWord);
	}

	@Override
	public long countMovie() {
		return movieRepository.count();
	}

	@Override
	public void sortEpisode(Movie movie) {
		for (int i = 0; i < movie.getEpisodeSeriesList().size(); i++) {
			for (int j = i + 1; j < movie.getEpisodeSeriesList().size(); j++) {
				if (movie.getEpisodeSeriesList().get(i).getOrdinalNumbers() > movie.getEpisodeSeriesList().get(j)
						.getOrdinalNumbers()) {
					Episode temp = new Episode();
					temp = movie.getEpisodeSeriesList().get(i);
					movie.getEpisodeSeriesList().set(i, movie.getEpisodeSeriesList().get(j));
					movie.getEpisodeSeriesList().set(j, temp);
				}
			}
		}
	}

	@Override
	public List<Movie> findByYearProduce(Integer year) {
		return movieRepository.findByYearProduce(year);
	}

}
