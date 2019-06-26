package com.javawebspringboot.websitemovie.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_movie")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovie;

	@Column(name = "name_movie")
	private String nameMovie;

	

	@Column(name = "status")
	private Integer status;

	@Column(name = "content")
	private String content;

	@Column(name = "link_movie")
	private String linkMovie;

	@Column(name = "number_episode_movie")
	private int numberEpisodeMovie;

	@Column(name = "datetime_post")
	private LocalDateTime datetimePost;

	@Column(name = "language")
	private String language;

	@Column(name = "view")
	private Integer view;

	@Column(name = "year_produce")
	private int yearProduce;

	@ManyToOne
	@JoinColumn(name = "id_director")
	private Director director;

	@ManyToOne
	@JoinColumn(name = "id_country_produce")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "id_user_post")
	private User userPost;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "movie_category", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id_category"))
	private List<Category> categoryList;

	@ManyToMany
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor"))
	private List<Actor> actorList;

	@OneToMany(mappedBy = "movie")
	private List<Episode> episodeSeriesList;

	@OneToMany(mappedBy = "movie")
	private List<Trailer> trailerList;

	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
	private Slide slide;

	@Transient
	private MultipartFile avatar;

	@Transient
	private MultipartFile slideImg;

	public Movie() {
		super();
	}

	public Movie(String nameMovie,  Integer status, String content, String linkMovie,
			int numberEpisodeMovie, LocalDateTime datetimePost, String language, Integer view, int yearProduce,
			Director director, Country country, User userPost, List<Category> categoryList, List<Actor> actorList,
			List<Episode> episodeSeriesList, List<Trailer> trailerList, Slide slide, MultipartFile avatar,
			MultipartFile slideImg) {
		super();
		this.nameMovie = nameMovie;
		this.status = status;
		this.content = content;
		this.linkMovie = linkMovie;
		this.numberEpisodeMovie = numberEpisodeMovie;
		this.datetimePost = datetimePost;
		this.language = language;
		this.view = view;
		this.yearProduce = yearProduce;
		this.director = director;
		this.country = country;
		this.userPost = userPost;
		this.categoryList = categoryList;
		this.actorList = actorList;
		this.episodeSeriesList = episodeSeriesList;
		this.trailerList = trailerList;
		this.slide = slide;
		this.avatar = avatar;
		this.slideImg = slideImg;
	}

	
	
	
	
	public List<Trailer> getTrailerList() {
		return trailerList;
	}

	public void setTrailerList(List<Trailer> trailerList) {
		this.trailerList = trailerList;
	}

	public List<Episode> getEpisodeSeriesList() {
		return episodeSeriesList;
	}

	public void setEpisodeSeriesList(List<Episode> episodeSeriesList) {
		this.episodeSeriesList = episodeSeriesList;
	}

	public Slide getSlide() {
		return slide;
	}

	public void setSlide(Slide slide) {
		this.slide = slide;
	}

	public User getUserPost() {
		return userPost;
	}

	public void setUserPost(User userPost) {
		this.userPost = userPost;
	}

	public MultipartFile getSlideImg() {
		return slideImg;
	}

	public void setSlideImg(MultipartFile slideImg) {
		this.slideImg = slideImg;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public String getNameMovie() {
		return nameMovie;
	}

	public void setNameMovie(String nameMovie) {
		this.nameMovie = nameMovie;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkMovie() {
		return linkMovie;
	}

	public void setLinkMovie(String linkMovie) {
		this.linkMovie = linkMovie;
	}

	public int getNumberEpisodeMovie() {
		return numberEpisodeMovie;
	}

	public void setNumberEpisodeMovie(int numberEpisodeMovie) {
		this.numberEpisodeMovie = numberEpisodeMovie;
	}

	public LocalDateTime getDatetimePost() {
		return datetimePost;
	}

	public void setDatetimePost(LocalDateTime datetimePost) {
		this.datetimePost = datetimePost;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public int getYearProduce() {
		return yearProduce;
	}

	public void setYearProduce(int yearProduce) {
		this.yearProduce = yearProduce;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

}
