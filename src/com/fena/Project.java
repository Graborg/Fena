package com.fena;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Project {

	@SerializedName("id")
	private int id;
	@SerializedName("title")
	private String title;
	@SerializedName("subheading")
	private String subheading;
	@SerializedName("requested_skills")
	private String requested_skills;
	@SerializedName("gains")
	private String gains;
	@SerializedName("description")
	private String description;
	@SerializedName("time_plan")
	private String time_plan;
	@SerializedName("created_at")
	private String created_at;
	@SerializedName("updated_at")
	private String updated_at;
	@SerializedName("account_id")
	private int account_id;
	@SerializedName("show_project")
	private int show_project;
	@SerializedName("image")
	private int image;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubheading() {
		return subheading;
	}

	public String getRequested_skills() {
		return requested_skills;
	}

	public String getGains() {
		return gains;
	}

	public String getDescription() {
		return description;
	}

	public String getTimePlan() {
		if(time_plan == null){
			return "No Timeplan";
		}
		return time_plan;
	}
	
	public String getCreated(){
		return created_at;
	}
	
	public Date getUpdated(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = new Date(0);
		try {
			date = dateFormat.parse(updated_at);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public int getAccountId(){
		return account_id;
	}
	public int getShowProject(){
		return show_project;
	}
	public int getImage(){
		return image;
	}
}