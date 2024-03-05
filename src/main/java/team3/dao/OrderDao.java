package team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import team3.entity.Item_order;

public class OrderDao {
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
	
	public Item_order getOrder(String uid, int status) {
		Connection conn = getConnection();
		String sql = "select * from item_order where uid=? and status=?";
		
		Item_order order = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, uid);
			pstmt.setInt(2, status);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String time = rs.getString(4);
				time = time.replace(" ", "T");
				
				order = new Item_order(rs.getInt(1), rs.getString(2), rs.getInt(3), LocalDateTime.parse(time), rs.getInt(5));
			}
			
			conn.close();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return order;
	}
	
	public Item_order getOrderOid(int oid) {
		Connection conn = getConnection();
		String sql = "select * from item_order where oid=?";
		
		Item_order order = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, oid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String time = rs.getString(4);
				time = time.replace(" ", "T");
				
				order = new Item_order(rs.getInt(1), rs.getString(2), rs.getInt(3), LocalDateTime.parse(time), rs.getInt(5));
			}
			
			conn.close();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return order;
	}
	
	public List<Item_order> getOrderList(String uid) {
		Connection conn = getConnection();
		String sql = "select o.* from item_order o"
//				+ " join users u on u.uid=o.uid"
				+ " where o.uid=?"
				+ " order by o.oid desc";
		
		List<Item_order> list = new ArrayList<Item_order>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String time = rs.getString(4);
				time = time.replace(" ", "T");
				
				Item_order order = new Item_order(rs.getInt(1), rs.getString(2), rs.getInt(3),
						LocalDateTime.parse(time), rs.getInt(5));
				
				list.add(order);
			}
			
			conn.close();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void insertOrder(Item_order order) {
		Connection conn = getConnection();
		String sql = "insert into item_order values (default, ?, default, default, default)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, order.getUid());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateOrder(Item_order order) {
		Connection conn = getConnection();
		String sql = "update item_order set totalPrice=?, status=? where oid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, order.getTotalPrice());
			pstmt.setInt(2, order.getStatus());
			pstmt.setInt(3, order.getOid());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOrder(int oid) {
		Connection conn = getConnection();
		String sql = "delete from item_order where oid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, oid);
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
