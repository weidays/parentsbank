package com.weidays.parentsbank.server.dao;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.vo.PictureShareVo;


public interface ISharePicDao
{

	/**
	 * 保存图片分享的基本信息
	 * @param picShare
	 * @return
	 */
	int savePicShare(PictureShareBo picShare);
	/**
	 * 保存图片和分享的关联关系
	 * @param shareId
	 * @param picId
	 * @return
	 */
	boolean savePicShareRel(Integer shareId, Integer picId);
	/**
	 * 删除图片分享
	 * @param shareId
	 */
	boolean delPicShare(Integer shareId);
	/**
	 * 删除图片分享关联关系
	 * @param shareId
	 */
	boolean delPicShareRel(Integer shareId);
	/**
	 * 根据分享ID查找分享详情
	 * @param shareId
	 * @return
	 */
	List getShareDetailByShareId(Integer shareId);
	/**
	 * 通过shareId拿到picIds用$拼接
	 * @param shareId
	 * @return
	 */
	List<Integer> getSharePicRelByShareId(Integer shareId);
	/**
	 * 复制内容
	 * @param picShare
	 * @return
	 */
	int copyFromReship(PictureShareBo picShare);
	/**
	 * 复制关联图片
	 * @param newShareId
	 * @param fromShareId
	 */
	void saveNewPicShareRel(int newShareId, int fromShareId);
	/**
	 * 根据用户ID 获取用户图片分享信息
	 * @param hilifeId
	 * @param start
	 * @param newsFeedPageSize
	 * @return
	 */
	List getNewsFeed(Integer hilifeId, int start, int newsFeedPageSize);
	/**
	 * 根据shareId获取相关联的图片的URL
	 * @param shareId
	 * @return
	 */
	List<String> getPicUrls(Integer shareId);
	/**
	 * 获取count
	 * @param hilifeId
	 * @return
	 */
	int getNewsFeedCount(Integer hilifeId);
	/**
	 * 与我相关
	 * @param hilifeId
	 * @param start
	 * @param newsFeedPageSize
	 * @return
	 */
	List<PictureShareVo> getAtMe(Integer hilifeId, int start,
			int newsFeedPageSize);
	/**
	 * 与我相关总数
	 * @param hilifeId
	 * @return
	 */
	int getAtMeCount(Integer hilifeId);
	
}
