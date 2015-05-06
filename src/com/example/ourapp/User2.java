package com.example.ourapp;



public class User2{
private int id;
private String name;
//password,location,email
private String phone_number;
private String email;
private String password;
public User2(String mobile, String password) {
	
	this.phone_number = mobile;
	this.password = password;
}
public User2(){
	
}
public User2(int id,String name, String mobile, String email, String password) {
	this.id=id;
	this.name = name;
	this.phone_number = mobile;
	this.email = email;
	this.password = password;
}
public User2(String name, String mobile, String email, String password) {
	
	this.name = name;
	this.phone_number = mobile;
	this.email = email;
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return phone_number;
}
public void setMobile(String mobile) {
	this.phone_number = mobile;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

}

