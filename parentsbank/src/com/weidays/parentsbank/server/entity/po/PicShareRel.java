package com.weidays.parentsbank.server.entity.po;

// Generated 2013-12-16 16:00:06 by Hibernate Tools 3.4.0.CR1

/**
 * PicShareRel generated by hbm2java
 */
public class PicShareRel implements java.io.Serializable {

	private Integer relId;
	private int picId;
	private int shareId;

	public PicShareRel() {
	}

	public PicShareRel(int picId, int shareId) {
		this.picId = picId;
		this.shareId = shareId;
	}

	public Integer getRelId() {
		return this.relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	public int getPicId() {
		return this.picId;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}

	public int getShareId() {
		return this.shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

}