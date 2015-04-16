package com.fena;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

public class Mail {
	private Activity activity;
	private String token;
	
	public Mail(Activity activity){
		this.activity = activity;
		token = LogIn.account.getToken();
	}
	
	public void sendToPerson(int toId, Object attach){
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("to_person_id", toId);
			if(attach instanceof Person){
				jsonObj.put("attach_person_id", ((Person) attach).getId());
			}
			else if (attach instanceof Project){
				jsonObj.put("attach_project_id", ((Project) attach).getId());
			}
			System.out.println(jsonObj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String url = "https://connectionboard.se/mail/mail_account";
		JsonMailPost callbackservice3 = new JsonMailPost(activity, token, jsonObj.toString()) {
			//behövs ???
			@SuppressWarnings("unchecked")
			@Override
			public void receiveData(Object object) {
				//profile_id = (Integer) object;
			}
		};
		callbackservice3.execute(url, null, null);
	}
	
	public void sendToProject(int toId, Object attach){
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("to_project_id", toId);
			if(attach instanceof Person){
				jsonObj.put("attach_person_id", ((Person) attach).getId());
			}
			else if (attach instanceof Project){
				jsonObj.put("attach_project_id", ((Project) attach).getId());
			}
			System.out.println(jsonObj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String url = "https://connectionboard.se/mail/mail_account";
		JsonMailPost callbackservice3 = new JsonMailPost(activity, token, jsonObj.toString()) {
			//behövs ???
			@SuppressWarnings("unchecked")
			@Override
			public void receiveData(Object object) {
				//profile_id = (Integer) object;
			}
		};
		callbackservice3.execute(url, null, null);
	}
}
