package com.javawebspringboot.websitemovie.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "director")
public class Director implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_director")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDirector;

	@Column(name = "name_director")
	private String nameDirector;

	@Column(name = "code_director")
	private String codeActor;

	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "director")
	private List<Movie> movieList = new ArrayList<>();

	public Director() {
		super();
	}

	public Director(String nameDirector, String codeActor, Country country, List<Movie> movieList) {
		super();
		this.nameDirector = nameDirector;
		this.codeActor = codeActor;
		this.country = country;
		this.movieList = movieList;
	}

	public String getCodeActor() {
		return codeActor;
	}

	public void setCodeActor(String codeActor) {
		this.codeActor = codeActor;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}

	public String getNameDirector() {
		return nameDirector;
	}

	public void setNameDirector(String nameDirector) {
		this.nameDirector = nameDirector;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

}
