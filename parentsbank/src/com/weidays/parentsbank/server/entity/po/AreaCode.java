package com.weidays.parentsbank.server.entity.po;

// Generated 2013-12-7 18:49:37 by Hibernate Tools 3.4.0.CR1

/**
 * AreaCodeId generated by hbm2java
 */
public class AreaCode implements java.io.Serializable {

	private int areaCode;
	private String areaName;
	private String FAreaCode;
	private String sortAreaName;
	private int areaLevel;
	private String remark;
	private String status;

	public AreaCode() {
	}

	public AreaCode(int areaCode, String areaName, String FAreaCode,
			String sortAreaName, int areaLevel, String status) {
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.FAreaCode = FAreaCode;
		this.sortAreaName = sortAreaName;
		this.areaLevel = areaLevel;
		this.status = status;
	}

	public AreaCode(int areaCode, String areaName, String FAreaCode,
			String sortAreaName, int areaLevel, String remark, String status) {
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.FAreaCode = FAreaCode;
		this.sortAreaName = sortAreaName;
		this.areaLevel = areaLevel;
		this.remark = remark;
		this.status = status;
	}

	public int getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getFAreaCode() {
		return this.FAreaCode;
	}

	public void setFAreaCode(String FAreaCode) {
		this.FAreaCode = FAreaCode;
	}

	public String getSortAreaName() {
		return this.sortAreaName;
	}

	public void setSortAreaName(String sortAreaName) {
		this.sortAreaName = sortAreaName;
	}

	public int getAreaLevel() {
		return this.areaLevel;
	}

	public void setAreaLevel(int areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AreaCode))
			return false;
		AreaCode castOther = (AreaCode) other;

		return (this.getAreaCode() == castOther.getAreaCode())
				&& ((this.getAreaName() == castOther.getAreaName()) || (this
						.getAreaName() != null
						&& castOther.getAreaName() != null && this
						.getAreaName().equals(castOther.getAreaName())))
				&& ((this.getFAreaCode() == castOther.getFAreaCode()) || (this
						.getFAreaCode() != null
						&& castOther.getFAreaCode() != null && this
						.getFAreaCode().equals(castOther.getFAreaCode())))
				&& ((this.getSortAreaName() == castOther.getSortAreaName()) || (this
						.getSortAreaName() != null
						&& castOther.getSortAreaName() != null && this
						.getSortAreaName().equals(castOther.getSortAreaName())))
				&& (this.getAreaLevel() == castOther.getAreaLevel())
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null && castOther.getRemark() != null && this
						.getRemark().equals(castOther.getRemark())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAreaCode();
		result = 37 * result
				+ (getAreaName() == null ? 0 : this.getAreaName().hashCode());
		result = 37 * result
				+ (getFAreaCode() == null ? 0 : this.getFAreaCode().hashCode());
		result = 37
				* result
				+ (getSortAreaName() == null ? 0 : this.getSortAreaName()
						.hashCode());
		result = 37 * result + this.getAreaLevel();
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}
