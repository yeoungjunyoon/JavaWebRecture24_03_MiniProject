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

import team3.entity.Reply;


public class ReplyDao {
	
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
	
	public Reply getReply(int rid) {
		Connection conn = getConnection();
		String sql = "select * from reply where rid=?";
		
		Reply r = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String time = rs.getString(5);
				time = time.replace(" ", "T");
			
				r = new Reply(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), LocalDateTime.parse(time));
			}
			
			conn.close();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	public List<Reply> getReplyList(int bid) {
		Connection conn = getConnection();
		String sql = "SELECT r.*, u.uname FROM reply r"
				+ " JOIN users u ON r.uid=u.uid"
				+ "	WHERE r.bid=?"
				+ "	ORDER BY rid";
		
		List<Reply> list = new ArrayList<Reply>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String time = rs.getString(5);
				time = time.replace(" ", "T");
			
				Reply r = new Reply(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), LocalDateTime.parse(time), rs.getString(6));
				
				list.add(r);
			}
			
			conn.close();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void insertReply(Reply reply) {
		Connection conn = getConnection();
		String sql = "insert into reply values (default, ?, ?, ?, default)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reply.getBid());
			pstmt.setString(2, reply.getUid());
			pstmt.setString(3, reply.getContent());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateReply(Reply reply) {
		Connection conn = getConnection();
		String sql = "update reply set content=?, replyTime=now() where rid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getRid());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteReply(int rid) {
		Connection conn = getConnection();
		String sql = "delete from reply where rid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rid);
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getReplyCount(int bid) {
		Connection conn = getConnection();
		String sql = "SELECT COUNT(rid) FROM reply b"
				+ "	WHERE b.bid=?";
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				count = rs.getInt(1);
			}
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
}
