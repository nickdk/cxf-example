package com.xti.nickdk.resources;

public class Country {
	
	private String id;
	private String name;
	
	public Country(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
