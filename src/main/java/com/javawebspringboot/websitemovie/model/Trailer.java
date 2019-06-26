package com.javawebspringboot.websitemovie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trailer")
public class Trailer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idTrailer")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTrailer;

	@Column(name = "link_trailer")
	private String linkTrailer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_movie")
	private Movie movie;

	@Column(name = "ordinal_numbers")
	private Integer ordinalNumbers;

	public Trailer() {
		super();
	}

	public Trailer(String linkTrailer, Movie movie, Integer ordinalNumbers) {
		super();
		this.linkTrailer = linkTrailer;
		this.movie = movie;
		this.ordinalNumbers = ordinalNumbers;
	}

	public Integer getIdTrailer() {
		return idTrailer;
	}

	public void setIdTrailer(Integer idTrailer) {
		this.idTrailer = idTrailer;
	}

	public String getLinkTrailer() {
		return linkTrailer;
	}

	public void setLinkTrailer(String linkTrailer) {
		this.linkTrailer = linkTrailer;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Integer getOrdinalNumbers() {
		return ordinalNumbers;
	}

	public void setOrdinalNumbers(Integer ordinalNumbers) {
		this.ordinalNumbers = ordinalNumbers;
	}

}
