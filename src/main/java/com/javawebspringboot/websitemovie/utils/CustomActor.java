package com.javawebspringboot.websitemovie.utils;

public class CustomActor {
	private int idActor;

	private String nameActor;

	public CustomActor(int idActor, String nameActor) {
		super();
		this.idActor = idActor;
		this.nameActor = nameActor;
	}

	public CustomActor() {
		super();
	}

	public int getIdActor() {
		return idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}

	public String getNameActor() {
		return nameActor;
	}

	public void setNameActor(String nameActor) {
		this.nameActor = nameActor;
	}

}
