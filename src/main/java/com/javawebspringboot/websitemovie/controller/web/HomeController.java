package com.javawebspringboot.websitemovie.controller.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javawebspringboot.websitemovie.model.Actor;
import com.javawebspringboot.websitemovie.model.Category;
import com.javawebspringboot.websitemovie.model.Country;
import com.javawebspringboot.websitemovie.model.Episode;
import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Trailer;
import com.javawebspringboot.websitemovie.service.ActorService;
import com.javawebspringboot.websitemovie.service.CategoryService;
import com.javawebspringboot.websitemovie.service.CountryService;
import com.javawebspringboot.websitemovie.service.EpisodeService;
import com.javawebspringboot.websitemovie.service.MovieService;
import com.javawebspringboot.websitemovie.service.SlideService;
import com.javawebspringboot.websitemovie.service.TrailerService;
import com.javawebspringboot.websitemovie.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private SlideService slideService;

	@Autowired
	private UserService userService;

	@Autowired
	private EpisodeService episodeService;

	@Autowired
	private TrailerService trailerService;

	@RequestMapping(value = { "/", "/user" })
	public String showHomePage(Model model) {

		model.addAttribute("title",
				"Phim Hay | Phim HD Vietsub | Xem Phim Online | Xem Phim Nhanh | Mọt Phim - Motphim");

		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());

		model.addAttribute("listMovieSC", movieService.findTop10MovieComingSoon());
		model.addAttribute("slides", slideService.findAllSlideByStatus1());
		model.addAttribute("movie", new Movie());
		model.addAttribute("movieList", movieService.findTop16MovieNewUpdate());

		model.addAttribute("movieListOrderByView", movieService.findTop12ByOrderByViewDesc());

		return "web/home";
	}

	@RequestMapping("/quoc-gia/{codeCountry}")
	public String showMovieByCountry(Model model, @PathVariable(name = "codeCountry") String codeCountry,
			@RequestParam("page") Optional<Integer> page) {
		// tim quoc gia
		Country country = countryService.findByCodeCountry(codeCountry);
		Sort sortable = Sort.by("datetimePost").descending();
		int size = 16;
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<Movie> movieCountryList = movieService.findAllMovieByContry(country, pageable);

		int totalPages = movieCountryList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("codeCountry", country.getCodeCountry());

		String strCountry = "Phim " + country.getNameCountry() + " Mới Nhất";

		// ket qua loc theo quoc gia
		model.addAttribute("title", strCountry);
		model.addAttribute("strCountry", strCountry.toUpperCase());
		model.addAttribute("movieCountryList", movieCountryList);

		// menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("listMovieSC", movieService.findTop10MovieComingSoon());

		return "web/movieCountry";
	}

	@RequestMapping("/phim/{linkMovie}")
	public String showMovieDescription(Model model, @PathVariable(name = "linkMovie") String linkMovie) {

		// ket qua click vao mot bo phim
		Movie movie = movieService.findByLinkMovie(linkMovie);
		movieService.updateMovie(movie);
		model.addAttribute("title", "Phim " + movie.getNameMovie());
		model.addAttribute("movie", movie);

		// tao menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("listMovieSC", movieService.findTop10MovieComingSoon());

		return "web/movieDescription";
	}

	@RequestMapping("/xem-phim/{linkEpisode}")
	public String playMovie(Model model, @PathVariable(name = "linkEpisode") String linkEpisode) {

		Episode episode = episodeService.findByLinkEpisode(linkEpisode);

		// xem 1 bo phim
		Movie movie = episode.getMovie();
		movieService.sortEpisode(movie);
		model.addAttribute("title", "Phim " + movie.getNameMovie());
		model.addAttribute("movie", movie);
		model.addAttribute("episode", episode);

		// tao menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());

		return "web/playMovie";
	}

	@RequestMapping("/xem-trailer/{linkTrailer}")
	public String playTrailer(Model model, @PathVariable(name = "linkTrailer") String linkTrailer) {

		Trailer trailer = trailerService.findByLinkTrailer(linkTrailer);

		// xem 1 bo phim
		Movie movie = trailer.getMovie();
		movieService.sortEpisode(movie);
		model.addAttribute("title", "Phim " + movie.getNameMovie());
		model.addAttribute("movie", movie);
		model.addAttribute("trailer", trailer);

		// tao menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());

		return "web/playTrailer";
	}

	@RequestMapping("/the-loai/{codeCategory}")
	public String showmovieCategory(Model model, @PathVariable(name = "codeCategory") String codeCategory) {
		// menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("listMovieSC", movieService.findTop10MovieComingSoon());

		// tim kiem theo the loai
		Category category = categoryService.findByCodeCategory(codeCategory);
		String strCategory = "Phim " + category.getNameCategory();
		model.addAttribute("title", strCategory);
		model.addAttribute("strCategory", strCategory.toUpperCase());
		model.addAttribute("category", category);

		return "web/movieCategory";
	}

	@RequestMapping("/dien-vien/{codeActor}")
	public String showMovieActor(Model model, @PathVariable(name = "codeActor") String codeActor) {

		// tim phim cua dien vien
		Actor actor = actorService.findActorByCodeActor(codeActor);
		String txtActorMovie = "Phim " + actor.getNameActor();
		model.addAttribute("title", txtActorMovie);
		model.addAttribute("txtActorMovie", txtActorMovie.toUpperCase());
		model.addAttribute("movieActor", actor);

		// menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("listMovieSC", movieService.findTop10MovieComingSoon());
		// menu
		return "web/movieActor";
	}

	@RequestMapping("/user/download-movie/{linkEpisode}")
	public void downloadMovie(@PathVariable(name = "linkEpisode") String linkEpisode, HttpServletResponse response) {
		episodeService.downloadMovie(linkEpisode, response);

	}

	@RequestMapping("/user/download-trailer/{linkTrailer}")
	public void downloadTrailer(@PathVariable(name = "linkTrailer") String linkTrailer, HttpServletResponse response) {
		episodeService.downloadTrailer(linkTrailer, response);

	}

	@RequestMapping("/tim-kiem-phim/")
	public String search(Model model, @RequestParam(name = "keyWord") String keyWord) {
		List<Movie> movieList = movieService.searchMovie(keyWord);

		model.addAttribute("movieList", movieList);
		model.addAttribute("keyWord", keyWord);

		// menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("listMovieSC", movieService.findTop10MovieComingSoon());
		// menu
		return "web/searchMovie";
	}

	@RequestMapping("/dang-ky-tai-khoan")
	public String registerAccount(@RequestParam(name = "emailRegister") String emailRegister,
			@RequestParam(name = "passwordRegister") String passwordRegister) {
		System.out.println("emailRegister " + emailRegister + " passwordRegister " + passwordRegister);
		boolean check = userService.registerAccount(emailRegister, passwordRegister);
		if (check == true) {
			return "redirect:/user";

		} else {

			return "redirect:/";
		}
	}

	@RequestMapping("/phim-moi/{year}")
	public String getMovieByYear(Model model, @PathVariable(name = "year") Integer year) {
		model.addAttribute("year", year);

		for (Movie movie : movieService.findByYearProduce(year)) {
			System.out.println("movie " + movie.getNameMovie());
		}

		model.addAttribute("movieList", movieService.findByYearProduce(year));
		// menu
		model.addAttribute("categoryList", categoryService.findAllCategory());
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("listMovieSC", movieService.findTop10MovieComingSoon());
		// menu
		return "web/movieByYear";
	}

}
