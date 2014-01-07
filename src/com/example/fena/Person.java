package com.example.fena;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

public class Person {
	@SerializedName("id")
	private Integer id;
	@SerializedName("name")
	private String name;
	@SerializedName("userId")
	private Integer userId;
	@SerializedName("expectations")
	private String expectations;
	@SerializedName("skills")
	private String skills;
	@SerializedName("description")
	private String description;
	@SerializedName("experience")
	private String experience;
	@SerializedName("created_at")
	private String created_at;
	@SerializedName("updated_at")
	private String updated_at;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getExpectations() {
		return expectations;
	}

	public String getSkills() {
		return skills;
	}

	public String getDescription() {
		return description;
	}

	public String experience() {
		return experience;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void SetExpectations(String expectations) {
		this.expectations = expectations;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExperience() {
		this.experience = experience;
	}
}