package com.oddrock.netease.oddbuy.entity;

public class Trx {
	private Long id;
	private Long contentId;
	private Long personId;
	private Long price;
	private java.math.BigInteger time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public java.math.BigInteger getTime() {
		return time;
	}
	public void setTime(java.math.BigInteger time) {
		this.time = time;
	}
	
}
