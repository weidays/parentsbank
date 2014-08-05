package com.weidays.parentsbank.server.entity.po;

// Generated 2013-12-28 11:06:12 by Hibernate Tools 3.4.0.CR1


/**
 * PictureShare generated by hbm2java
 */
public class PictureShare implements java.io.Serializable {

	private Integer shareId;
	private int hilifeId;
	private String title;
	private String content;
	private String sendTime;
	private String sendCity;
	private String sentLocation;
	private double longitude;
	private double latitude;
	private String source;
	private int fromShareId;

	public PictureShare() {
	}

	public PictureShare(int hilifeId, String title, String content,
			String sendTime, String sendCity, String sentLocation,
			double longitude, double latitude, int fromShareId) {
		this.hilifeId = hilifeId;
		this.title = title;
		this.content = content;
		this.sendTime = sendTime;
		this.sendCity = sendCity;
		this.sentLocation = sentLocation;
		this.longitude = longitude;
		this.latitude = latitude;
		this.fromShareId = fromShareId;
	}

	public PictureShare(int hilifeId, String title, String content,
			String sendTime, String sendCity, String sentLocation,
			double longitude, double latitude, String source, int fromShareId) {
		this.hilifeId = hilifeId;
		this.title = title;
		this.content = content;
		this.sendTime = sendTime;
		this.sendCity = sendCity;
		this.sentLocation = sentLocation;
		this.longitude = longitude;
		this.latitude = latitude;
		this.source = source;
		this.fromShareId = fromShareId;
	}

	public Integer getShareId() {
		return this.shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
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

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getFromShareId() {
		return this.fromShareId;
	}

	public void setFromShareId(int fromShareId) {
		this.fromShareId = fromShareId;
	}

}