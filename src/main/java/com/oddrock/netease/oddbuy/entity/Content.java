package com.oddrock.netease.oddbuy.entity;

import java.util.Arrays;

public class Content {
	private Long id;
	private Long price;
	private String title;
	private byte[] icon;
 	private String image;
	private String summary;
	private byte[] text;
	private String detail;
	private int buyNum;
	private boolean isBuy;
	private boolean isSell;
	private int buyPrice;
	private int saleNum;
	
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public boolean isBuy() {
		return isBuy;
	}
	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}
	public boolean isSell() {
		return isSell;
	}
	public void setSell(boolean isSell) {
		this.isSell = isSell;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public int getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
		this.saleNum = buyNum;
		if(this.buyNum>0) {
			this.isBuy=true;
			this.isSell=true;
		}else {
			this.isBuy=false;
			this.isSell=false;
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
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
		if(null!=icon){  
			this.image=new String(icon);  
        }  
        return this.image; 
	}
	public void setImage(String image) {
		if(null!=image) {
			this.image = image;
			this.icon=image.getBytes();
		}
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public byte[] getText() {
		return text;
	}
	public void setText(byte[] text) {
		this.text = text;
		if(null!=text){  
			this.detail=new String(text);  
        }  
	}
	public void setDetail(String detail) {
		if(null!=detail) {
			this.detail = detail;
			this.text=detail.getBytes();
		}
	}
	public String getDetail() {
		if(null!=text){  
			this.detail=new String(text);  
        }  
        return this.detail; 
	}
	@Override
	public String toString() {
		return "Content [id=" + id + ", price=" + price + ", title=" + title + ", icon=" + Arrays.toString(icon)
				+ ", image=" + image + ", summary=" + summary + ", text=" + Arrays.toString(text) + ", detail=" + detail
				+ ", buyNum=" + buyNum + ", isBuy=" + isBuy + ", isSell=" + isSell + ", buyPrice=" + buyPrice
				+ ", saleNum=" + saleNum + "]";
	}
	
}
