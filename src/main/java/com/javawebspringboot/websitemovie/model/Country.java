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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_country")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCountry;

	@Column(name = "name_country")
	private String nameCountry;

	@Column(name = "code_country")
	private String codeCountry;

	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Movie> movieList = new ArrayList<>();

	public Country() {
		super();
	}

	public Country(String nameCountry, String codeCountry, List<Movie> movieList) {
		super();
		this.nameCountry = nameCountry;
		this.codeCountry = codeCountry;
		this.movieList = movieList;
	}

	public int getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public String getNameCountry() {
		return nameCountry;
	}

	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}

	public String getCodeCountry() {
		return codeCountry;
	}

	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

}
