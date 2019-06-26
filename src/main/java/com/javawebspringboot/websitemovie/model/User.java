package com.javawebspringboot.websitemovie.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "datetime_created")
	private LocalDateTime datetimeCreatedAccount;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
	private List<Role> roleList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userPost")
	private List<Movie> movieList = new ArrayList<>();

	public User() {
		super();
	}

	public User(String email, String password, LocalDateTime datetimeCreatedAccount, List<Role> roleList,
			List<Movie> movieList) {
		super();
		this.email = email;
		this.password = password;
		this.datetimeCreatedAccount = datetimeCreatedAccount;
		this.roleList = roleList;
		this.movieList = movieList;
	}

	public LocalDateTime getDatetimeCreatedAccount() {
		return datetimeCreatedAccount;
	}

	public void setDatetimeCreatedAccount(LocalDateTime datetimeCreatedAccount) {
		this.datetimeCreatedAccount = datetimeCreatedAccount;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
