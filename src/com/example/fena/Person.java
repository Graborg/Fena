package com.example.fena;

import com.google.gson.annotations.SerializedName;

public class Person {
	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	@SerializedName("expectations")
	private String expectations;
	@SerializedName("skills")
	private String skills;
	@SerializedName("description")
	private String description;
	@SerializedName("mail")
	private String mail;
	@SerializedName("created_at")
	private String created_at;
	@SerializedName("updated_at")
	private String updated_at;
	@SerializedName("account_id")
	private int account_id;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public String getMail() {
		return mail;
	}
	
	public String getCreated(){
		return created_at;
	}
	
	public String getUpdated(){
		return updated_at;
	}
	
	public int getAccountId(){
		return account_id;
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
}