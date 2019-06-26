package com.javawebspringboot.websitemovie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "slide")
public class Slide implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_slide")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSlide;

	@Column(name = "link_image")
	private String linkImage;

	@OneToOne
	@JoinColumn(name = "id_movie")
	private Movie movie;

	@Column(name = "status")
	private Integer status;

	public Slide() {
		super();
	}

	public Slide(String linkImage, Movie movie, Integer status) {
		super();
		this.linkImage = linkImage;
		this.movie = movie;
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getIdSlide() {
		return idSlide;
	}

	public void setIdSlide(int idSlide) {
		this.idSlide = idSlide;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
