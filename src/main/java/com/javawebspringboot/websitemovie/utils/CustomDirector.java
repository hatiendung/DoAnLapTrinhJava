package com.javawebspringboot.websitemovie.utils;

public class CustomDirector {
	private int idDirector;

	private String nameDirector;

	public CustomDirector() {
		super();
	}

	public CustomDirector(int idDirector, String nameDirector) {
		super();
		this.idDirector = idDirector;
		this.nameDirector = nameDirector;
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

}
