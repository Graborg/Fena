package com.example.fena;
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
		return time_plan;
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
}