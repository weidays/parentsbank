package com.weidays.parentsbank.server.entity.vo;


import java.util.Date;
import java.util.List;

/**
 * 用于展示评论的 VO
 */
public class CommentLogVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4022027611085346956L;
	private Integer commentId;
	private int busiId;
	private int PCommentId;
	private int hilifeId;
	private String nickname;
	private Date commentTime;
	private String content;
	private String busiType;
	private List<CommentLogVo> children;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public int getBusiId() {
		return busiId;
	}
	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}
	public int getPCommentId() {
		return PCommentId;
	}
	public void setPCommentId(int pCommentId) {
		PCommentId = pCommentId;
	}
	public int getHilifeId() {
		return hilifeId;
	}
	public void setHilifeId(int hilifeId) {
		this.hilifeId = hilifeId;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public List<CommentLogVo> getChildren() {
		return children;
	}
	public void setChildren(List<CommentLogVo> children) {
		this.children = children;
	}



}
