package team3.service;

import java.util.List;

import team3.entity.Reply;

public interface ReplyService {

	public static final int COUNT_PER_PAGE = 10;
	
	Reply getReply(int rid);

	List<Reply> getReplyList(int bid);
	
	void insertReply(Reply reply);
	
	void updateReply(Reply reply);
	
	void deleteReply(int rid);
}
