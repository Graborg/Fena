package com.example.fena;

import java.util.ArrayList;

import android.content.SharedPreferences.Editor;

public class Account {
	private String token;
	private int account_id;

	public Account(String token, String account_id, boolean keepsignin) {
		this.token = token;
		this.account_id = Integer.parseInt(account_id);
		if(keepsignin){
			Editor editor = Splash.sharedpreferences.edit();
			editor.putString("token", this.token);
			editor.putInt("account_id", this.account_id);
			editor.commit();
		}
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