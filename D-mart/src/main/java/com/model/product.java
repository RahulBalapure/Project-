package com.model;

public class product {
	private int pid;
	private String pname;
	private String pdiscription;
	private String ptype;
	private double pprice;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdiscription() {
		return pdiscription;
	}

	public void setPdiscription(String pdiscription) {
		this.pdiscription = pdiscription;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public double getPprice() {
		return pprice;
	}

	public void setPprice(double pprice) {
		this.pprice = pprice;
	}

	@Override
	public String toString() {
		return "product [pid=" + pid + ", pname=" + pname + ", pdiscription=" + pdiscription + ", ptype=" + ptype
				+ ", pprice=" + pprice + "]";
	}

}
