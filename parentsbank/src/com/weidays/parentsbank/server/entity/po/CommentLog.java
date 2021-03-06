package com.weidays.parentsbank.server.entity.po;

// Generated 2013-12-24 16:43:01 by Hibernate Tools 3.4.0.CR1


/**
 * CommentLog generated by hbm2java
 */
public class CommentLog implements java.io.Serializable {

	private Integer commentId;
	private int busiId;
	private int PCommentId;
	private int hilifeId;
	private String commentTime;
	private String content;
	private int topCommentId;
	private String busiType;

	public CommentLog() {
	}

	public CommentLog(int busiId, int PCommentId, int hilifeId,
			String commentTime, String content, int topCommentId) {
		this.busiId = busiId;
		this.PCommentId = PCommentId;
		this.hilifeId = hilifeId;
		this.commentTime = commentTime;
		this.content = content;
		this.topCommentId = topCommentId;
	}

	public CommentLog(int busiId, int PCommentId, int hilifeId,
			String commentTime, String content, int topCommentId, String busiType) {
		this.busiId = busiId;
		this.PCommentId = PCommentId;
		this.hilifeId = hilifeId;
		this.commentTime = commentTime;
		this.content = content;
		this.topCommentId = topCommentId;
		this.busiType = busiType;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public int getBusiId() {
		return this.busiId;
	}

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public int getPCommentId() {
		return this.PCommentId;
	}

	public void setPCommentId(int PCommentId) {
		this.PCommentId = PCommentId;
	}

	public int getHilifeId() {
		return this.hilifeId;
	}

	public void setHilifeId(int hilifeId) {
		this.hilifeId = hilifeId;
	}

	public String getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTopCommentId() {
		return this.topCommentId;
	}

	public void setTopCommentId(int topCommentId) {
		this.topCommentId = topCommentId;
	}

	public String getBusiType() {
		return this.busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

}
