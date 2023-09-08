package com.example.demo.model;

//import lombok.Getter;

public class FileUp {
	private String name;
	
	private String description;
	
	public FileUp file;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
