package com.oddrock.netease.oddbuy.entity;

public class Person {
	private Long id;
	private String userName;
	private String nickName;
	private Integer userType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", userName=" + userName + ", nickName=" + nickName + ", userType=" + userType
				+ "]";
	}
	
}
