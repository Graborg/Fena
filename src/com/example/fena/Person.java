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
	@SerializedName("created_at")
	private String created_at;
	@SerializedName("updated_at")
	private String updated_at;
	@SerializedName("account_id")
	private int account_id;
	@SerializedName("show_profile")
	private int show_profile;
	@SerializedName("image")
	private int image;

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

	public String getCreated(){
		return created_at;
	}
	
	public String getUpdated(){
		return updated_at;
	}
	
	public int getAccountId(){
		return account_id;
	}

	public int getShowProfile(){
		return show_profile;
	}
	public int getImage(){
		return image;
	}
}