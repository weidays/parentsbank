package com.weidays.parentsbank.server.service;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.UserInfo;

public interface IIndexService{
	
	/**
	 * 获取首页
	 * @param userInfo 
	 * @param hilifeId
	 * @param start
	 * @throws Exception 
	 */
	public List getNewsFeed(UserInfo userInfo, int start,int pageSize) throws Exception;
	/**
	 * 获取总共条数
	 * @param userInfo 
	 * @return
	 */
	public int getNewsFeedCount(UserInfo userInfo);
	

}
