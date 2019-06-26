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
@Table(name = "episode")
public class Episode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_episode")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEpisode;

	@Column(name = "link_episode")
	private String linkEpisode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_movie")
	private Movie movie;

	@Column(name = "ordinal_numbers")
	private Integer ordinalNumbers;

	public Episode() {
		super();
	}

	public Episode(String linkEpisode, Movie movie, Integer ordinalNumbers) {
		super();
		this.linkEpisode = linkEpisode;
		this.movie = movie;
		this.ordinalNumbers = ordinalNumbers;
	}

	public Integer getOrdinalNumbers() {
		return ordinalNumbers;
	}

	public void setOrdinalNumbers(Integer ordinalNumbers) {
		this.ordinalNumbers = ordinalNumbers;
	}

	public Integer getIdEpisode() {
		return idEpisode;
	}

	public void setIdEpisode(Integer idEpisode) {
		this.idEpisode = idEpisode;
	}

	public String getLinkEpisode() {
		return linkEpisode;
	}

	public void setLinkEpisode(String linkEpisode) {
		this.linkEpisode = linkEpisode;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
