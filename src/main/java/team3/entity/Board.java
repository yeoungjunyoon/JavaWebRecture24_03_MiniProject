package team3.entity;

import java.time.LocalDateTime;

public class Board {
	private int bid;
	private String uid;
	private String title;
	private String content;
	private LocalDateTime writeTime;
	private int viewCount;
	private int replyCount;
	private String uname;
	
	public Board() {
	}

	public Board(String uid, String title, String content) {
		this.uid = uid;
		this.title = title;
		this.content = content;
	}

	public Board(int bid, String uid, String title, String content) {
		this.bid = bid;
		this.uid = uid;
		this.title = title;
		this.content = content;
	}

	public Board(String uid, String title, String content, LocalDateTime writeTime, int viewCount, int replyCount) {
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.writeTime = writeTime;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
	}

	public Board(int bid, String uid, String title, String content, LocalDateTime writeTime, int viewCount,
			int replyCount) {
		this.bid = bid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.writeTime = writeTime;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
	}

	public Board(int bid, String uid, String title, String content, LocalDateTime writeTime, int viewCount,
			int replyCount, String uname) {
		this.bid = bid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.writeTime = writeTime;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", uid=" + uid + ", title=" + title + ", content=" + content + ", writeTime="
				+ writeTime + ", viewCount=" + viewCount + ", replyCount=" + replyCount + "]";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(LocalDateTime writeTime) {
		this.writeTime = writeTime;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
}
