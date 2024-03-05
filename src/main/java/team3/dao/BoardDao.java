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

import team3.entity.Board;

public class BoardDao {

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
	
	public Board getBoard(int bid) {
		Connection conn = getConnection();
		String sql = "SELECT b.*, u.uname FROM board b"
				+ "	JOIN users u ON b.uid = u.uid"
				+ "	WHERE b.bid=?";
		
		Board b = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String time = rs.getString(5);
				time = time.replace(" ", "T");
				
				b = new Board(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), LocalDateTime.parse(time), rs.getInt(6),
						rs.getInt(7), rs.getString(8));
			}
			
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	// field는 제목, 내용, 작성ID 등 검색 옵션
	// query 값은 검색어
	public List<Board> getBoardList(String field, String query, int num, int offset) {
		Connection conn = getConnection();
		String sql = "SELECT b.*, u.uname FROM board b"
				+ "	JOIN users u ON b.uid=u.uid"
				+ "	WHERE " + field + " LIKE ?"
				+ "	ORDER BY bid DESC"
				+ "	LIMIT ? OFFSET ?";
		
		List<Board> list = new ArrayList<Board>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%" + query + "%");
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String time = rs.getString(5);
				time = time.replace(" ", "T");
				
				Board b = new Board(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), LocalDateTime.parse(time), rs.getInt(6),
						rs.getInt(7), rs.getString(8));
				
				list.add(b);
			}
			
			conn.close();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public void insertBoard(Board board) {
		Connection conn = getConnection();
		String sql = "insert into board values(default, ?, ?, ?, default, default, default)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getUid());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setReplyCount(Board board) {
		
		Connection conn = getConnection();
		String sql = "update board set replycount=? where bid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, board.getBid());
			pstmt.setInt(1, board.getReplyCount());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateBoard(Board board) {
		Connection conn = getConnection();
		String sql = "update board set title=?, content=?, writeTime=now() where bid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBid());
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBoard(int bid) {
		Connection conn = getConnection();
		String sql = "delete from board where bid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// field는 view 또는 reply
	public void increaseCount(String field, int bid) {
		Connection conn = getConnection();
		String sql = "UPDATE board SET " + field + "Count = " + field + "Count + 1 WHERE bid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getBoardCount(String field, String query) {
		Connection conn = getConnection();
		String sql = "SELECT COUNT(bid) FROM board b"
				+ "	JOIN users ON b.uid=users.uid"
				+ "	WHERE " + field + " LIKE ?";
		
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