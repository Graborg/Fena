package com.example.fena;
import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

public class Person {
	@Expose
	Integer id;
	@Expose
	Integer userId;
	@Expose
	String name;
	@Expose
	String expectations;
	@Expose
	String skills; 
	@Expose
	String description;
	@Expose
	String experience;
	@Expose
	String created_at;
	@Expose
	String updated_at;
	
	public Integer getId(){
		return id;
	}
	public Integer getUserId(){
		return userId;
	}
	public String getName(){
		return name;
	}
	public String getExpectations(){
		return expectations;
	}
	public String getSkills(){
		return skills;
	}
	public String getDescription(){
		return description;
	}
	public String experience(){
		return experience;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public void setName(String name){
		this.name = name;
	}
	public void SetExpectations(String expectations){
		this.expectations = expectations;
	}
	public void setSkills(String skills){
		this.skills = skills;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setExperience(){
		this.experience = experience;
	}
}
