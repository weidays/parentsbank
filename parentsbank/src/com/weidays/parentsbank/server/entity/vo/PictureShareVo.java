package com.weidays.parentsbank.server.entity.vo;

// Generated 2013-12-16 16:00:06 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.List;

import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.entity.po.BankOwner;

/**
 * PictureShare generated by hbm2java
 */
public class PictureShareVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4798155520702206192L;
	private Integer shareId;
	private int hilifeId;
	private String nickname;
	private String title;
	private String content;
	private String sendTime;
	private String sendCity;
	private String sentLocation;
	private double longitude;
	private double latitude;
	private String source;
	private int fromShareId;
	private List<Integer> picIds;
	private List picUrls;
	private List<CommentLogVo> comments;
	private int favourCount;
	private int commentsCount;
	private BankOwner userInfo;
	

	public Integer getShareId() {
		return this.shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public int getHilifeId() {
		return this.hilifeId;
	}

	public void setHilifeId(int hilifeId) {
		this.hilifeId = hilifeId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendCity() {
		return this.sendCity;
	}

	public void setSendCity(String sendCity) {
		this.sendCity = sendCity;
	}

	public String getSentLocation() {
		return this.sentLocation;
	}

	public void setSentLocation(String sentLocation) {
		this.sentLocation = sentLocation;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public BankOwner getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(BankOwner userInfo) {
		this.userInfo = userInfo;
	}

	

	public List getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(List picUrls) {
		this.picUrls = picUrls;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getFromShareId() {
		return fromShareId;
	}

	public void setFromShareId(int fromShareId) {
		this.fromShareId = fromShareId;
	}




	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<CommentLogVo> getComments() {
		return comments;
	}

	public void setComments(List<CommentLogVo> comments) {
		this.comments = comments;
	}
	

	public int getFavourCount() {
		return favourCount;
	}

	public void setFavourCount(int favourCount) {
		this.favourCount = favourCount;
	}
	

	public List<Integer> getPicIds() {
		return picIds;
	}

	public void setPicIds(List<Integer> picIds) {
		this.picIds = picIds;
	}

	public void create(PictureShare x, List<Integer> picIds2,List<String> picUrls2) {
		this.picIds=picIds2;
		this.picUrls=picUrls2;
		this.content=x.getContent();
		this.hilifeId=x.getHilifeId();
		this.latitude=x.getLatitude();
		this.longitude=x.getLongitude();
		this.sendCity=x.getSendCity();
		this.sendTime=x.getSendTime();
		this.sentLocation=x.getSentLocation();
		this.shareId=x.getShareId();
		this.title=x.getTitle();
		this.source=x.getSource();
		this.fromShareId=x.getFromShareId();
	}
	

}
