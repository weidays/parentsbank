package com.weidays.parentsbank.server.service;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.vo.PictureShareVo;

public interface ISharePicService{
	/**
	 * 分享图片
	 * @param picShare
	 * @return
	 */
	public boolean sharePic(PictureShareBo picShare);
	/**
	 * 分享删除
	 * @param picShare
	 * @return
	 */
	public boolean shareDel(PictureShareBo picShare);
	/**
	 * 获取分享详情
	 * @param shareId
	 * @return
	 */
	public PictureShareVo getShareDetail(Integer shareId);
	/**
	 * 转载分享
	 * @param picShare
	 * @return 
	 */
	public boolean reshipPic(PictureShareBo picShare);
	/**
	 * 根据用户编号 获取该用户的 图片分享动态
	 * @param hilifeId
	 * @param start
	 * @param pageSize
	 */
	public List getNewsFeed(Integer hilifeId, int start, int pageSize);
	/**
	 * 获取总共条数
	 * @param hilifeId
	 * @return
	 */
	public int getNewsFeedCount(Integer hilifeId);
	/**
	 * 与我相关总数统计
	 * @param hilifeId
	 * @return
	 */
	public int getAtMeCount(Integer hilifeId);
	/**
	 * 与我相关
	 * @param hilifeId
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List getAtMe(Integer hilifeId, int start, int pageSize);
	

}
