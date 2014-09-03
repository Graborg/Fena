package com.example.fena;

import java.util.ArrayList;

public class Account {
	private String token;
	private int account_id;
	public int person_id;

	public Account(String token, String account_id) {
		this.token = token;
		this.account_id = Integer.parseInt(account_id);
		
	}

	public String getToken() {
		return token;
	}

	public int getAccountID() {
		return account_id;
	}

	public Person getMyProfile() {
		for (Person person : LogIn.persons) {
			if (person.getAccountId() == account_id) {
				return person;
			}
		}
		return null;
	}
	
	public ArrayList<Project> getMyProjects() {
		ArrayList<Project> projects = new ArrayList<Project>();
		for (Project proj: LogIn.projects){
			if(proj.getAccountId() == account_id){
				projects.add(proj);
			}
		}
		return projects;
	}
}