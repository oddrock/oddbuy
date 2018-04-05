package com.oddrock.netease.oddbuy.entity;
public class BuyItem{
		private Long id;
		private int number;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		@Override
		public String toString() {
			return "BuyItem [id=" + id + ", number=" + number + "]";
		}
		
		
		
	}