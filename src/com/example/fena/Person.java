package com.example.fena;

public class Person {
	private String name;
	private String description1;
	private String description2;
	private String description3;
	private String occupation;
	private String experience;
	
	public void setName(String name){
		this.name = name;
	}
	public void setDescription(String desc1, String desc2, String desc3){
		desc1 = description1;
		desc2 = description2;
		desc3 = description3;
	}
	public void setOccupation(String occupation){
		this.occupation = occupation;
	}
	public void setExperience(String experience){
		experience = experience;
	}
}
