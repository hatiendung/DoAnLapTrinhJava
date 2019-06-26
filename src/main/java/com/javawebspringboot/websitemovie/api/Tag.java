package com.javawebspringboot.websitemovie.api;

public class Tag {

	public int id;
	public String tagName;
	public int tuoi;

	public int getId() {
		return id;
	}
	
	

	public int getTuoi() {
		return tuoi;
	}



	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}



	public Tag(int id, String tagName, int tuoi) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.tuoi = tuoi;
	}

	

}