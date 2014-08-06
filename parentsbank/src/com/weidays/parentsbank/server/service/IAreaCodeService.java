package com.weidays.parentsbank.server.service;

import java.util.List;

import com.weidays.parentsbank.server.entity.po.BankOwner;

public interface IAreaCodeService{
	
	
	/**
	 * 获取已开放的区域列表
	 * @return
	 */
	public List getOpenedAreaList();
	

}
