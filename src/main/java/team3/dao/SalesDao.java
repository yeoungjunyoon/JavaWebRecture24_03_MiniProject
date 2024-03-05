package team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import team3.entity.Sales;

public class SalesDao {

	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/mini"); // 링크에 맞는 DataSource 호출
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public Sales getSales(int sid) {
		Connection conn = getConnection();
		String sql = "select * from sales where sid=?";
		
		Sales sales = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				sales = new Sales(sid, rs.getInt(2), rs.getInt(3), rs.getInt(4));
			}
			
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sales;
	}
	
	public Sales getSalesOid(int oid, int mid) {
		Connection conn = getConnection();
		String sql = "select * from sales where oid=? and mid=?";
		
		Sales sales = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			pstmt.setInt(2, mid);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				sales = new Sales(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
			}
			
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sales;
	}
	
	public List<Sales> getSalesList(int oid) {
		Connection conn = getConnection();
		String sql = "select s.*, b.price, b.name from sales s"
				+ " JOIN item b ON b.mid = s.mid"
				+ " where oid=?"
				+ " order by sid";
		
		List<Sales> list = new ArrayList<Sales>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Sales sales = new Sales(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6));
				
				list.add(sales);
			}
			
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void insertSales(Sales sales) {
		Connection conn = getConnection();
		String sql = "insert into sales values(default, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sales.getMid());
			pstmt.setInt(2, sales.getOid());
			pstmt.setInt(3, sales.getQuantity());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateSales(Sales sales) {
		Connection conn = getConnection();
		String sql = "update sales set mid=?, quantity=? where sid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sales.getMid());
			pstmt.setInt(2, sales.getQuantity());
			pstmt.setInt(3, sales.getSid());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSales(int sid)
	{
		Connection conn = getConnection();
		String sql = "delete from sales where sid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sid);
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
