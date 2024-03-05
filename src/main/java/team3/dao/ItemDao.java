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

import team3.entity.Item;

public class ItemDao {

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
	
	public Item getItem(int mid) {
		Connection conn = getConnection();
		String sql = "select * from item where mid=?";
		
		Item item = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String time = rs.getString(4);
				time = time.replace(" ", "T");
				
				item = new Item(rs.getInt(1), rs.getString(2), rs.getInt(3), LocalDateTime.parse(time),
						rs.getString(5), rs.getString(6), rs.getString(7));
			}
			
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	
	public List<Item> getItemList(String field, String query, int limit, int offset) {
		Connection conn = getConnection();
		String sql = "SELECT b.* FROM item b"
				+ "	WHERE b." + field + " LIKE ?"
				+ "	ORDER BY mid DESC"
				+ "	LIMIT ? OFFSET ?";
		
		List<Item> list = new ArrayList<Item>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + query + "%");
			pstmt.setInt(2, limit);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String time = rs.getString(4);
				time = time.replace(" ", "T");
				
				Item item = new Item(rs.getInt(1), rs.getString(2), rs.getInt(3), LocalDateTime.parse(time),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
				list.add(item);
			}
			
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void insertItem(Item item) {
		Connection conn = getConnection();
		String sql = "insert into item values (default, ?, ?, default, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getPrice());
			pstmt.setString(3, item.getCategory());
			pstmt.setString(4, item.getImage());
			pstmt.setString(5, item.getDescription());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateItem(Item item) {
		Connection conn = getConnection();
		String sql = "update item set name=?, price=?, itemTime=now(), category=?, image=?, description=? where mid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getPrice());
			pstmt.setString(3, item.getCategory());
			pstmt.setString(4, item.getImage());
			pstmt.setString(5, item.getDescription());
			pstmt.setInt(6, item.getMid());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteItem(int mid) {
		Connection conn = getConnection();
		String sql = "delete from item where mid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mid);
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getItemCount(String field, String query)
	{
		Connection conn = getConnection();
		
		String sql = "SELECT COUNT(mid) FROM item b"
				+ "	WHERE b." + field + " LIKE ?";
		
		int count = 0;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				count = rs.getInt(1);
			}
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
