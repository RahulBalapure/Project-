package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.staff;
import com.util.connectionClass;

public class staffDao {
	public int insertStaff(staff s) {
		int check = 0;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		String sql = "insert into staff (sname, semail, spassword) values (?,?,?) ";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s.getSname());
			pst.setString(2, s.getSemail());
			pst.setString(3, s.getSpassword());

			check = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(pst, con);
		}

		return check;
	}

	public int deleteStaffbyId(int id) {
		int check = 0;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;

		String sql = "delete from staff where sid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			check = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new connectionClass().connectionClose(pst, con);
		}

		return check;
	}

	public staff displayStaffById(int id) {
		staff s1 = null;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select sid ,sname,semail, spassword  from staff where sid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				s1 = new staff();
				s1.setSid((int) rs.getObject("sid"));
				s1.setSname((String) rs.getObject("sname"));
				s1.setSemail((String) rs.getObject("semail"));
				s1.setSpassword((String) rs.getObject("spassword"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(rs, pst, con);
		}

		return s1;
	}

	public List<staff> displayAllStaff() {
		List<staff> list = new ArrayList();
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select sid, sname, semail, spassword from staff ";

		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				staff s1 = new staff();
				s1.setSid((int) rs.getObject("sid"));
				s1.setSname((String) rs.getObject("sname"));
				s1.setSemail((String) rs.getObject("semail"));
				s1.setSpassword((String) rs.getObject("spassword"));

				list.add(s1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(rs, pst, con);
		}

		return list;

	}
	public staff staffLogin(String email, String password)
	{
		staff s1 = null;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst =null;
		ResultSet rs=null;
		String sql = "SELECT sid, sname, semail, spassword from staff where semail=? and spassword=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,email);
			pst.setString(2,password);
			rs=pst.executeQuery();
			while (rs.next())
			{
				s1=new staff();
				s1.setSid((int) rs.getObject("sid"));
				s1.setSname((String) rs.getObject("sname"));
				s1.setSemail((String) rs.getObject("semail"));
				s1.setSpassword((String) rs.getObject("spassword"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose( pst, con);
		}

		return s1;
	}
	public int updateStaff(staff s) {
		int check=0;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		String sql = "update staff set  sname=?, semail=?,"
				+ " spassword=? where sid=?";
	
		try {
			pst = con.prepareStatement(sql);
	
			pst.setString(1, s.getSname());
			pst.setString(2, s.getSemail());
			pst.setString(3, s.getSpassword());
			pst.setInt(4, s.getSid());
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			new connectionClass().connectionClose(pst, con);
		}
		return check;
	}
	

}
