package com.example.ourapp;

public class TaskPOJO {
	private int id;
	private int userid;
	private String name;
	private String image;
	private String location;
	
	TaskPOJO(int id, String name, String image, int userid, String location){
		this.id = id;
		this.name = name;
		this.image = image;
		this.userid = userid;
		this.location = location;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
