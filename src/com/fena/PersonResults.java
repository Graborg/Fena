package com.fena;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PersonResults {
	@SerializedName("persons")
	public List<Person> persons;
}
