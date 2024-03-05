package team3.entity;

import java.time.LocalDateTime;

public class Reply {
	private int rid;
	private int bid;
	private String uid;
	private String content;
	private LocalDateTime replyTime;
	private String uname;
	
	public Reply() {
	}
	
	public Reply(int bid, String uid, String content) {
		this.bid = bid;
		this.uid = uid;
		this.content = content;
	}
	
	public Reply(int rid, int bid, String uid, String content, LocalDateTime replyTime) {
		this.rid = rid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
		this.replyTime = replyTime;
	}
	
	public Reply(int rid, int bid, String uid, String content, LocalDateTime replyTime, String uname) {
		this.rid = rid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
		this.replyTime = replyTime;
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", bid=" + bid + ", uid=" + uid + ", content=" + content + ", replyTime="
				+ replyTime + "]";
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(LocalDateTime replyTime) {
		this.replyTime = replyTime;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
}
