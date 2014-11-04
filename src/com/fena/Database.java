package com.fena;

import java.util.ArrayList;
import android.app.Activity;

public class Database {

	public void update(Activity activity) {
		String url = "http://54.191.168.116:3001/persons/";
		JsonPersonreceiver callbackservice = new JsonPersonreceiver(activity) {
			@SuppressWarnings("unchecked")
			@Override
			public void receiveData(Object object) {
				LogIn.persons = (ArrayList<Person>) object;
			}

		};
		callbackservice.execute(url, null, null);

		// Project
		String url2 = "http://54.191.168.116:3001/projects/";
		JsonProjectreceiver callbackservice2 = new JsonProjectreceiver(activity) {
			@SuppressWarnings("unchecked")
			@Override
			public void receiveData(Object object) {
				LogIn.projects = (ArrayList<Project>) object;
			}
		};
		callbackservice2.execute(url2, null, null);
	}
}
