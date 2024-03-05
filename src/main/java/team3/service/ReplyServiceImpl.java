package team3.service;

import java.util.List;

import team3.dao.BoardDao;
import team3.dao.ReplyDao;
import team3.entity.Board;
import team3.entity.Reply;

public class ReplyServiceImpl implements ReplyService {
	
	private ReplyDao rDao = new ReplyDao();
	private BoardDao bDao = new BoardDao();

	@Override
	public Reply getReply(int rid) {
		return rDao.getReply(rid);
	}

	@Override
	public List<Reply> getReplyList(int bid) {
		
		List<Reply> list = rDao.getReplyList(bid);
		Board board = bDao.getBoard(bid);
		int replyCount = list.size();
		board.setReplyCount(replyCount);
		bDao.setReplyCount(board);
		
		return rDao.getReplyList(bid);
	}

	@Override
	public void insertReply(Reply reply) {
		Board board = bDao.getBoard(reply.getBid());
		int replyCount = board.getReplyCount() + 1;
		board.setReplyCount(replyCount);
		bDao.setReplyCount(board);
		
		rDao.insertReply(reply);
	}

	@Override
	public void updateReply(Reply reply) {
		rDao.updateReply(reply);
	}

	@Override
	public void deleteReply(int rid) {
		
		Board board = bDao.getBoard(rDao.getReply(rid).getBid());
		int replyCount = board.getReplyCount() - 1;
		board.setReplyCount(replyCount);
		bDao.setReplyCount(board);
		
		rDao.deleteReply(rid);
	}


}
