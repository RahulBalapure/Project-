package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;

import com.model.user;
import com.util.connectionClass;

public class userDao {
	public String generatePin() {
		String s = Integer.toString(new Random().nextInt(9999));
		for (int i = s.length(); i < 4; i++)
			s += "0";
		return s;
	}

	public String encryptPin(String s) {
		String s1 = Base64.getEncoder().encodeToString(s.getBytes());
		return s1;
	}

	public int getMaxAcNo() {
		int uacno = 0;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "Select max(uacno) as uacno from user1";

		try {

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				uacno = (int) rs.getObject("uacno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new connectionClass().connectionClose(rs, pst, con);

		}
		return uacno;
	}

	public String[] createAccount(user u) {
		String s1[] = null;
		Connection con = new connectionClass().connectionStart();
		u.setUacpin(generatePin());
		PreparedStatement pst = null;
		String sql = "insert into user1 (uacname, uactype, uacbalance, uacpin ) values (?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getUacname());
			pst.setString(2, u.getUactype());
			pst.setDouble(3, u.getUacamount());
			pst.setString(4, encryptPin(u.getUacpin()));
			if (pst.executeUpdate() > 0) {
				s1 = new String[2];
				s1[0] = Integer.toString(getMaxAcNo());
				s1[1] = u.getUacpin();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new connectionClass().connectionClose(pst, con);

		}

		return s1;
	}

	public boolean checkLogin(int uacno, String uacpin) {
		boolean b = false;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "Select uacno from user1 where uacno=? and uacpin=?";

		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, uacno);
			pst.setString(2, encryptPin(uacpin));
			rs = pst.executeQuery();
			while (rs.next()) {
				b = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new connectionClass().connectionClose(rs, pst, con);
		}

		return b;
	}

	public double checkBalance(int uacno, String uacpin) {
		double uacbalance = 0;
		if (checkLogin(uacno, uacpin)) {
			Connection con = new connectionClass().connectionStart();
			PreparedStatement pst = null;
			ResultSet rs = null;

			String sql = "Select uacbalance from user1 where uacno=? and uacpin=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, uacno);
				pst.setString(2, encryptPin(uacpin));
				rs = pst.executeQuery();
				while (rs.next()) {
					uacbalance = (double) rs.getObject("uacbalance");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				new connectionClass().connectionClose(rs, pst, con);

			}
		} else {
			System.out.println("invalid credential !!!");
		}

		return uacbalance;
	}

	public double depositeBalance(int uacno, String uacpin, double dbalance) {
		double ubalance = 0;
		if (checkLogin(uacno, uacpin)) {
			dbalance += checkBalance(uacno, uacpin);
			Connection con = new connectionClass().connectionStart();
			PreparedStatement pst = null;
			String sql = "Update user1 set uacbalance=? where uacno=? and uacpin=?";
			try {
				pst = con.prepareStatement(sql);
				pst.setDouble(1, dbalance);
				pst.setInt(2, uacno);
				pst.setString(3, encryptPin(uacpin));
				if (pst.executeUpdate() > 0) {
					ubalance = checkBalance(uacno, uacpin);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				new connectionClass().connectionClose(pst, con);

			}

		} else {
			System.out.println("invalid credential !!!");
		}

		return ubalance;

	}

	public double withdrawlBalance(int uacno, String uacpin, double dbalance) {
		double ubalance = 0;
		if (checkLogin(uacno, uacpin)) {
			dbalance = checkBalance(uacno, uacpin) - dbalance;
			if (dbalance >= 1000) {

				Connection con = new connectionClass().connectionStart();
				PreparedStatement pst = null;
				String sql = "Update user1 set uacbalance=? where uacno=? and uacpin=?";
				try {
					pst = con.prepareStatement(sql);
					pst.setDouble(1, dbalance);
					pst.setInt(2, uacno);
					pst.setString(3, encryptPin(uacpin));
					if (pst.executeUpdate() > 0) {
						ubalance = checkBalance(uacno, uacpin);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					new connectionClass().connectionClose(pst, con);

				}
			} else {
				System.out.println("insufficint Amuont !!!");
				ubalance = 0;
			}
		} else {
			System.out.println("invalid credential !!!");
		}

		return ubalance;

	}

	public user acountDetails(int uacno, String uacpin) {
		user u = null;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "Select uacno,uacname,uactype,uacbalance from user1 where uacno=? and uacpin=?";

		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, uacno);
			pst.setString(2, encryptPin(uacpin));
			rs = pst.executeQuery();
			while (rs.next()) {
				u = new user();
				u.setUacno((int) rs.getObject("uacno"));
				u.setUacname((String) rs.getObject("uacname"));
				u.setUactype((String) rs.getObject("uactype"));
				u.setUacamount((double) rs.getObject("uacbalance"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(rs, pst, con);
		}

		return u;
	}

	public boolean verifyPin(String nuacpin) {
		boolean b = false;
		int count = 0;
		if (nuacpin.length() == 4) {
			for (int i = 0; i < nuacpin.length(); i++)
				if (Character.isDigit(nuacpin.charAt(i)))
					count++;
		if (count == 4)
			b = true;
		}else {
			System.out.println("\n... Enter only Four Digite Pin..!! \n ");
		}
		return b;

	}

	public boolean changePin(int uacno, String uacpin, String nuacpin) {
		boolean b = false;
		b = verifyPin(nuacpin);
		int check=0;
		if (b) {
			Connection con = new connectionClass().connectionStart();
			PreparedStatement pst = null;
			String sql = "update user1 set uacpin=? where uacno=? and uacpin=?";

			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, encryptPin(nuacpin));
				pst.setInt(2, uacno);
				pst.setString(3, encryptPin(uacpin));
				check = pst.executeUpdate();
				if(check>0)
					b=true;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				new connectionClass().connectionClose(pst, con);
			}
			return b;
		}

		return b;
	}
	
	public boolean updatePin(user u)
	{
		boolean b=false;
		b=verifyPin(u.getUacpin());
		if (b) {
			Connection con = new connectionClass().connectionStart();
			PreparedStatement pst = null;
			String sql = "update user1 set uacpin=? where uacno=? and uacname=? and uactype=?";

			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, encryptPin(u.getUacpin()));
				pst.setInt(2, u.getUacno());
				pst.setString(3, u.getUacname());
				pst.setString(4, u.getUactype());
				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				new connectionClass().connectionClose(pst, con);
			}
		}
		  
		
		
		return b;
	}
	public user forgatePin(int uacno,String uacname, String uactype) {
		
		user u=null;
		Connection con =new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "Select uacno,uacname,uactype from user1 where uacno=? and uacname=? and uactype=?";

		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,uacno);
			pst.setString(2, uacname);
			pst.setString(3, uactype);
			rs = pst.executeQuery();
			while (rs.next()) {
				u = new user();
				u.setUacno((int) rs.getObject("uacno"));
				u.setUacname((String) rs.getObject("uacname"));
				u.setUactype((String) rs.getObject("uactype"));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(rs, pst, con);
		}
		
		
		return u;
	}

}
