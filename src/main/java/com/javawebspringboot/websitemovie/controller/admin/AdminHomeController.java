package com.javawebspringboot.websitemovie.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.websitemovie.model.Category;
import com.javawebspringboot.websitemovie.model.Episode;
import com.javawebspringboot.websitemovie.model.Movie;
import com.javawebspringboot.websitemovie.model.Slide;
import com.javawebspringboot.websitemovie.service.ActorService;
import com.javawebspringboot.websitemovie.service.CategoryService;
import com.javawebspringboot.websitemovie.service.CountryService;
import com.javawebspringboot.websitemovie.service.DirectorService;
import com.javawebspringboot.websitemovie.service.EpisodeService;
import com.javawebspringboot.websitemovie.service.MovieService;
import com.javawebspringboot.websitemovie.service.SlideService;
import com.javawebspringboot.websitemovie.service.TrailerService;
import com.javawebspringboot.websitemovie.service.UserService;

@Controller
public class AdminHomeController {

	@Autowired
	private EpisodeService episodeService;

	@Autowired
	private SlideService slideService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private TrailerService trailerService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private DirectorService directorService;

	@RequestMapping("/admin")
	public String showHomePage(Model model) {

		List<Movie> listMovie = movieService.findTop10ByOrderByViewDesc();
		List<Category> listCategorieList = categoryService.findAllCategory();

		List<String> lbCategory = new ArrayList<String>();
		List<Float> pointCategory = new ArrayList<>();

		if (listCategorieList.size() > 0) {
			categoryService.getDataChart(lbCategory, pointCategory, listCategorieList);
		}
		model.addAttribute("lbCategory", lbCategory);
		model.addAttribute("pointCategory", pointCategory);

		List<String> label = new ArrayList<String>();
		List<Integer> point = new ArrayList<Integer>();

		if (listMovie.size() > 0) {

			movieService.getDataChart(label, point, listMovie);

		}
		model.addAttribute("label", label);
		model.addAttribute("point", point);

		return "admin/home";
	}

	@RequestMapping("/admin/danh-sach-phim")
	public String showListMovie(Model model,@RequestParam("page") Optional<Integer> page) {
		
		Sort sortable = Sort.by("datetimePost").descending();
		int size = 15;
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<Movie> movieList = movieService.findAllMovie(pageable);

		int totalPages = movieList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		
		model.addAttribute("listMovie", movieList);
		return "admin/listMovie";
	}

	@RequestMapping("/admin/danh-sach-slide")
	public String showListSlide(Model model) {
		model.addAttribute("listSlide", slideService.findAllSlide());
		return "admin/listSlide";
	}

	@RequestMapping("/admin/them-phim-moi")
	public String addNewMovie(Model model) {

		model.addAttribute("movie", new Movie());
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("categorys", categoryService.findAllCategory());

		return "admin/addNewMovie";
	}

	@RequestMapping(value = "/admin/add-new-film", method = RequestMethod.POST)
	public String newMovie(@ModelAttribute("movie") Movie movie,
			@RequestParam(required = true, name = "countryId") Integer countryId,
			@RequestParam(name = "category", required = false, defaultValue = "0") List<Integer> listCategory) {

		movieService.saveMovie(movie, countryId, listCategory);
		return "redirect:/admin/them-phim-moi";
	}

	@RequestMapping("/admin/delete/{idMovie}")
	public String deleteMovie(@PathVariable(name = "idMovie") Integer idMovie) {
		movieService.deleteMovie(idMovie);
		return "redirect:/admin/danh-sach-phim";
	}

	@RequestMapping("/admin/xoa-slide/{idSlide}")
	public String deleteSlide(@PathVariable(name = "idSlide") Integer idSlide) {
		slideService.deleteSlideByIdSlide(idSlide);
		return "redirect:/admin/danh-sach-slide";
	}

	@RequestMapping("/admin/phim/{linkMovie}")
	public String movieDescriptionEpisode(Model model, @PathVariable(name = "linkMovie") String linkMovie) {
		Movie movie = movieService.findByLinkMovie(linkMovie);
		if (movie.getEpisodeSeriesList().size() > 0) {
			// co tap phim
			return "redirect:/admin/xem-phim/" + movie.getEpisodeSeriesList().get(0).getLinkEpisode();
		}
		model.addAttribute("movie", movie);
		model.addAttribute("episode", null);

		return "admin/play_descriptionMovie";
	}

	@RequestMapping("/admin/xem-phim/{linkEpisode}")
	public String playMovie(Model model, @PathVariable(name = "linkEpisode") String linkEpisode) {
		Episode episode = episodeService.findByLinkEpisode(linkEpisode);

		// xem 1 bo phim
		Movie movie = episode.getMovie();
		movieService.sortEpisode(movie);
		model.addAttribute("movie", movie);
		model.addAttribute("episode", episode);
		return "admin/play_descriptionMovie";
	}

	@RequestMapping(value = "/admin/phim/{linkMovie}/them-tap-phim-moi", method = RequestMethod.POST)
	public String addEpisode(@RequestParam(name = "videoEpisode") MultipartFile videoEpisode,
			@PathVariable(name = "linkMovie") String linkMovie) {
		episodeService.addNewEpisode(linkMovie, videoEpisode);
		return "redirect:/admin/phim/{linkMovie}";
	}

	@RequestMapping("/admin/danh-sach-user")
	public String listUser(Model model) {
		model.addAttribute("listUser", userService.findAllUser());
		return "admin/listUser";
	}

	@RequestMapping("/admin/update/{linkMovie}")
	public String updateMovie(@PathVariable(name = "linkMovie") String linkMovie, Model model) {
		Movie movieUpdate = movieService.findByLinkMovie(linkMovie);
		model.addAttribute("movieUpdate", movieUpdate);
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("categorys", categoryService.findAllCategory());
		model.addAttribute("categoryList", movieUpdate.getCategoryList());
		Slide slide = slideService.findByMovie(movieUpdate);
		String linkSlide = "";
		if (slide != null) {
			linkSlide = slide.getLinkImage();
		}
		model.addAttribute("linkSlide", linkSlide);
		return "admin/updateMovie";
	}

	@RequestMapping("/admin/delete-episode/{linkEpisode}")
	public String deleteEpisode(@PathVariable(name = "linkEpisode") String linkEpisode) {

		Episode episode = episodeService.findByLinkEpisode(linkEpisode);
		String linkMovie = episode.getMovie().getLinkMovie();

		episodeService.deleteEpisode(linkEpisode);

		return "redirect:/admin/phim/" + linkMovie;
	}

	@RequestMapping("/admin/trailer/{linkMovie}/them-trailer-phim")
	public String addNewTrailer(Model model, @PathVariable(name = "linkMovie") String linkMovie,
			@RequestParam(name = "videoTrailer") MultipartFile videoTrailer) {
		trailerService.addNewTrailer(linkMovie, videoTrailer);
		return "redirect:/admin/phim/{linkMovie}";
	}

	@RequestMapping("/admin/delete-trailer/{linkTrailer}/movie/{linkMovie}")
	public String deleteTrailer(@PathVariable(name = "linkTrailer") String linkTrailer,
			@PathVariable(name = "linkMovie") String linkMovie) {

		trailerService.deleteTrailer(linkTrailer);
		return "redirect:/admin/phim/{linkMovie}";
	}

	@RequestMapping("/admin/tim-kiem-phim/")
	public String searchMovie(Model model, @RequestParam(name = "keyWord") String keyWord) {
		List<Movie> movieList = movieService.searchMovie(keyWord);
		model.addAttribute("movieList", movieList);
		model.addAttribute("keyWord", keyWord);
		return "admin/searchMovie";
	}

	@RequestMapping("/admin/actor-director")
	public String actorDirector(Model model) {
		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("actorList", actorService.findAllActor());
		model.addAttribute("directorList", directorService.findAllDerector());
		return "admin/actorDirector";
	}

	@RequestMapping("/admin/add-new-actor-director")
	public String addNewActorDirector(@RequestParam(name = "name") String name,
			@RequestParam(name = "actor_director") Integer actor_director,
			@RequestParam(name = "countryId") Integer idCountry) {

		if (actor_director == 1) {
			actorService.addNewActor(name, idCountry);
		}
		if (actor_director == 2) {

			directorService.addNewDirector(name, idCountry);
		}
		return "redirect:/admin/actor-director";
	}
	
	
	@RequestMapping("/admin/phim-khong-duoc-chieu")
	public String movieDisable(Model model) {
		model.addAttribute("movieList", movieService.findAllByEnableOrderByDatetimePost());
		return "admin/movieDisable";
	}

}
