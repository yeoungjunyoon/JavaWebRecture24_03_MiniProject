package team3.service;

import java.util.List;

import team3.dao.BoardDao;
import team3.dao.ReplyDao;
import team3.entity.Board;

public class BoardServiceImpl implements BoardService {
	
	private BoardDao bDao = new BoardDao();
	private ReplyDao rDao = new ReplyDao();

	@Override
	public List<Board> getBoardList(int page, String field, String query) {
		int offset = (page - 1) * BoardService.COUNT_PER_PAGE;
		
		List<Board> list = bDao.getBoardList(field, query, BoardService.COUNT_PER_PAGE, offset);
		for (int i = 0; i < list.size(); i++)
		{
			int bid = list.get(i).getBid();
			list.get(i).setReplyCount(rDao.getReplyCount(bid));
		}
		
		return list;
	}

	@Override
	public Board getBoard(int bid) {		
		return bDao.getBoard(bid);
	}

	@Override
	public int getBoardCount(String field, String query) {
		return bDao.getBoardCount(field, query);
	}

	@Override
	public void insertBoard(Board board) {
		bDao.insertBoard(board);
	}

	@Override
	public void updateBoard(Board board) {
		bDao.updateBoard(board);
	}

	@Override
	public void deleteBoard(int bid) {
		bDao.deleteBoard(bid);
	}

	@Override
	public void increaseViewCount(int bid) {
		bDao.increaseCount("view", bid);
	}

	@Override
	public void increaseReplyCount(int bid) {
		bDao.increaseCount("reply", bid);		
	}

}
