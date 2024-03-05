package team3.service;

import java.util.List;

import team3.entity.Board;

public interface BoardService {
	
	public static final int COUNT_PER_PAGE = 10;

	List<Board> getBoardList(int page, String field, String query);
	
	Board getBoard(int bid);
	
	int getBoardCount(String field, String query);
	
	void insertBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(int bid);	
	
	void increaseViewCount(int bid);
	
	void increaseReplyCount(int bid);
}
