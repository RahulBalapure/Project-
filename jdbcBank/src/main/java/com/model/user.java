package com.model;

public class user {
	private int uacno;
	private String uacname;
	private String uactype;
	private String uacpin;
	private double uacbalance;
	public int getUacno() {
		return uacno;
	}
	public void setUacno(int uacno) {
		this.uacno = uacno;
	}
	public String getUacname() {
		return uacname;
	}
	public void setUacname(String uacname) {
		this.uacname = uacname;
	}
	public String getUactype() {
		return uactype;
	}
	public void setUactype(String uactype) {
		this.uactype = uactype;
	}
	public String getUacpin() {
		return uacpin;
	}
	public void setUacpin(String uacpin) {
		this.uacpin = uacpin;
	}
	public double getUacamount() {
		return uacbalance;
	}
	public void setUacamount(double uacamount) {
		this.uacbalance = uacamount;
	}
	@Override
	public String toString() {
		return "user [uacno=" + uacno + ", uacname=" + uacname + ", uactype=" + uactype + ", uacpin=" + uacpin
				+ ", uacamount=" + uacbalance + "]";
	}
	
	
}
