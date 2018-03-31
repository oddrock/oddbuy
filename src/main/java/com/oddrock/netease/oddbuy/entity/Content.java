package com.oddrock.netease.oddbuy.entity;

public class Content {
	private Long id;
	private Long price;
	private String title;
	private String image;
	private String summary;
	private String detail;
	private int buyCount;
	private boolean isBuy;
	private boolean isSell;
	
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
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "Content [id=" + id + ", price=" + price + ", title=" + title + ", image=" + image + ", summary="
				+ summary + ", detail=" + detail + ", buyCount=" + buyCount + ", isBuy=" + isBuy + ", isSell=" + isSell
				+ "]";
	}
	
	
}
