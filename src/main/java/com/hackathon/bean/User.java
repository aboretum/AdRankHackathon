package com.hackathon.bean;



/**
 * User bean to handle transfer of user data.
 */
public class User {
	private String userName;
	private String passWord;
	private String userEmail;
	private String userAvatar;
	
	public User(){
		
	}
		
	public String getUserName(){
		
		return userName;
	}
	
	public void setUserName(String userName){
		
		this.userName = userName;
	}
	
	public void setPassWord(String passWord){
		
		this.passWord = passWord;
	}
	
	public String getPassWord(){
		
		return passWord;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	
}
