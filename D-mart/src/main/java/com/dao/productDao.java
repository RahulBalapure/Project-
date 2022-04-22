package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.product;
import com.util.connectionClass;

public class productDao {
	public int insertProduct(product p) {
		int check = 0;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		String sql = "insert into product (pname, pdiscription, ptype, pprice) values (?,?,?,?) ";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getPname());
			pst.setString(2, p.getPdiscription());
			pst.setString(3, p.getPtype());
			pst.setDouble(4, p.getPprice());
			check = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(pst, con);
		}

		return check;
	}

	public int deleteProductbyId(int id) {
		int check = 0;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;

		String sql = "delete from product where pid=?";
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

	public product displayProductById(int id) {
		product p1 = null;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select pid ,pname, pdiscription, ptype, pprice from product where pid=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				p1 = new product();
				p1.setPid((int) rs.getObject("pid"));
				p1.setPname((String) rs.getObject("pname"));
				p1.setPdiscription((String) rs.getObject("pdiscription"));
				p1.setPtype((String) rs.getObject("ptype"));
				p1.setPprice((double) rs.getObject("pprice"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(rs, pst, con);
		}

		return p1;
	}

	public List<product> displayAllProduct() {
		List<product> list = new ArrayList();
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select pid, pname, pdiscription,ptype,pprice from product ";
		
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				product p1 = new product();
				p1.setPid((int) rs.getObject("pid"));
				p1.setPname((String) rs.getObject("pname"));
				p1.setPdiscription((String) rs.getObject("pdiscription"));
				p1.setPtype((String) rs.getObject("ptype"));
				p1.setPprice((double) rs.getObject("pprice"));
				list.add(p1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			new connectionClass().connectionClose(rs, pst, con);
		}

		return list;

	}
	
	public List<product> displayAllProductByAny(String s)
	{
		List<product> list =new ArrayList();
		Connection con =new  connectionClass().connectionStart();
		PreparedStatement pst =null;
		ResultSet rs =null;
		 String sql= "Select pid, pname, pdiscription,ptype,pprice from product "
		 		+ " where pname like '%"+s+"%' or pdiscription like '%"+s+"%' or "
		 			  + " ptype like '%"+s+"%' ";
		 boolean b=false;
		 int num =0;
		 try {
			 num=Integer.parseInt(s);
			 b=true;
			 sql+=" or pid = " +num+ " or pprice like '%"+num+"%' ";
			 
		 }catch(NumberFormatException e) {
		 }
		 try {
			pst=con.prepareStatement(sql);
			rs= pst.executeQuery();
			while (rs.next())
			{
				product p1=new product();
				p1.setPid((int ) rs.getObject("pid"));
				p1.setPname((String ) rs.getObject("pname"));
				p1.setPdiscription((String) rs.getObject("pdiscription"));
				p1.setPtype((String ) rs.getObject("ptype"));
				p1.setPprice((double) rs.getObject("pprice"));
				list.add(p1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 finally {
			 new connectionClass().connectionClose(rs, pst, con);
		 }
		
		
		return list;
	}
	public int updateProduct(product p) {
		int check=0;
		Connection con = new connectionClass().connectionStart();
		PreparedStatement pst = null;
		String sql = "update product set  pname=?, pdiscription=?,"
				+ " ptype=? ,pprice=? where pid=?";
	
		try {
			pst = con.prepareStatement(sql);
	
			pst.setString(1, p.getPname());
			pst.setString(2, p.getPdiscription());
			pst.setString(3, p.getPtype());
			pst.setDouble(4, p.getPprice());
			pst.setInt(5, p.getPid());
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
