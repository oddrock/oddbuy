package com.oddrock.netease.oddbuy.impure;

public class Account {
	private String title;
	private byte[] icon;
 	private String image;
	private Long id;
	private java.math.BigInteger buyTime;
	private Long buyNum;
	private Long buyPrice;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
		if(null!=icon){  
			this.image=new String(icon);  
        }
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public java.math.BigInteger getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(java.math.BigInteger buyTime) {
		this.buyTime = buyTime;
	}
	public Long getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Long buyNum) {
		this.buyNum = buyNum;
	}
	public Long getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Long buyPrice) {
		this.buyPrice = buyPrice;
	}
	
}
