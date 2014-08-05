package com.weidays.parentsbank.server.dao;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.UserInfo;


public interface IIndexDao
{


	/**
	 * 根据用户ID 获取用户图片分享信息
	 * @param hilifeId
	 * @param start
	 * @param newsFeedPageSize
	 * @return
	 */
	List getNewsFeed(UserInfo userInfo,int start, int newsFeedPageSize);

	/**
	 * 获取count
	 * @return
	 */
	int getNewsFeedCount(UserInfo userInfo);
	public List getPicUrls(Integer shareId);
	
}
