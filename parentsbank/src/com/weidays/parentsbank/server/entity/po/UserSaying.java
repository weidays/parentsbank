package com.weidays.parentsbank.server.entity.po;

// Generated 2013-12-5 22:19:48 by Hibernate Tools 3.4.0.CR1


/**
 * UserSaying generated by hbm2java
 */
public class UserSaying implements java.io.Serializable {

	private Integer sayingId;
	private Integer hilifeId;
	private String sendTime;
	private String sendContent;

	public UserSaying() {
	}

	public UserSaying(Integer hilifeId, String sendTime, String sendContent) {
		this.hilifeId = hilifeId;
		this.sendTime = sendTime;
		this.sendContent = sendContent;
	}

	public Integer getSayingId() {
		return this.sayingId;
	}

	public void setSayingId(Integer sayingId) {
		this.sayingId = sayingId;
	}

	public Integer getHilifeId() {
		return this.hilifeId;
	}

	public void setHilifeId(Integer hilifeId) {
		this.hilifeId = hilifeId;
	}


	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendContent() {
		return this.sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

}
