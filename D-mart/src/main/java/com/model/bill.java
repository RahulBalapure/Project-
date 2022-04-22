package com.model;

public class bill {
	private int bid;
	private String bdiscription;
	private double bamount;
	private String bgby;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBdiscription() {
		return bdiscription;
	}
	public void setBdiscription(String bdiscription) {
		this.bdiscription = bdiscription;
	}
	public double getBamount() {
		return bamount;
	}
	public void setBamount(double bamount) {
		this.bamount = bamount;
	}
	public String getBgby() {
		return bgby;
	}
	public void setBgby(String bgby) {
		this.bgby = bgby;
	}
	@Override
	public String toString() {
		return "bill [bid=" + bid + ", bdiscription=" + bdiscription + ", bamount=" + bamount + ", bgby=" + bgby + "]";
	}
	
	
}

