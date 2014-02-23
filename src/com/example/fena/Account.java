package com.example.fena;

public class Account {
	private String token;
	private String account_id;
	
	public Account(String token, String account_id) {
	this.token = token;
	this.account_id = account_id;
	System.out.println("Accountclass: " + "token: " + token + "account_id: " + account_id);
	}
	public String getToken() {
		return token;
	}
	public String getAccountID() {
		return account_id;
	}
	

}