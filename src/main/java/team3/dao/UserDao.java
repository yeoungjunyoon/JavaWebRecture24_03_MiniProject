package team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import team3.entity.Users;


public class UserDao {
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
	
	public Users getUsersByUid(String uid) {
		Connection conn = getConnection();
		String sql = "select * from Users where uid=? and isDeleted=0";
		
		Users u = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				u = new Users(uid, rs.getString(2), rs.getString(3),
						rs.getString(4), LocalDate.parse(rs.getString(5)), rs.getInt(6), rs.getString(7));
			}
			pstmt.close();
			rs.close();
			conn.close();
			
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Users> getUsersList(int num, int offset) {
		Connection conn = getConnection();
		String sql = "select * from Users where isDeleted=0"
				+ " order by regDate desc, uid limit ? offset ?";
		
		List<Users> list = new ArrayList<Users>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, offset);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Users u = new Users(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4),
						LocalDate.parse(rs.getString(5)), rs.getInt(6), rs.getString(7));
				
				list.add(u);
			}
			
			pstmt.close();
			rs.close();
			conn.close();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void insertUsers(Users Users) {
		Connection conn = getConnection();
		String sql = "insert into Users values(?, ?, ?, ?, default, default, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Users.getUid());
			pstmt.setString(2, Users.getPwd());
			pstmt.setString(3, Users.getUname());
			pstmt.setString(4, Users.getEmail());
			pstmt.setString(5, Users.getAddress());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUsers(Users Users) {
		Connection conn = getConnection();
		String sql = "update Users set pwd=?, uname=?, email=?, address=? where uid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Users.getPwd());
			pstmt.setString(2, Users.getUname());
			pstmt.setString(3, Users.getEmail());
			pstmt.setString(4, Users.getAddress());
			pstmt.setString(5, Users.getUid());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUsers(String uid) {
		Connection conn = getConnection();
		String sql = "update Users set isDeleted=1 where uid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getUsersCount() {
		Connection conn = getConnection();
		String sql = "Select count(uid) from Users where isDeleted=0";
		
		int count = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				count = rs.getInt(1);
			}
			rs.close(); stmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}