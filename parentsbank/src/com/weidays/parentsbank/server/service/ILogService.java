package com.weidays.parentsbank.server.service;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.CommentLog;
import com.weidays.parentsbank.server.entity.po.FavourLog;

public interface ILogService{
	public int getCommentsCount(int busiId, String busiType);
	/**
	 * 获取评论
	 * @param busiId
	 * @param busiType
	 * @param newsFeedPageSize 
	 * @param start 
	 * @return
	 */
	public List getComment(int busiId, String busiType, int start, int newsFeedPageSize);
	/**
	 * 获取赞数量
	 * @param busiId
	 * @param busiType
	 * @return 
	 */
	public int getFavourCount(int busiId, String busiType);
	/**
	 * 点赞
	 * @param favourLog
	 * @return
	 */
	public int favourIt(FavourLog favourLog);
	/**
	 * 统计评论数量
	 * @param busiId
	 * @param busiType
	 * @return
	 */
	public int getCommentCount(int busiId, String busiType);
	/**
	 * 评论提交
	 * @param commentLog
	 * @return
	 */
	public int commentIt(CommentLog commentLog);
	
	

}
