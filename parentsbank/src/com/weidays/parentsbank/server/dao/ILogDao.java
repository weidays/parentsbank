package com.weidays.parentsbank.server.dao;

import java.util.List;

import com.weidays.parentsbank.server.entity.po.CommentLog;
import com.weidays.parentsbank.server.entity.po.FavourLog;
import com.weidays.parentsbank.server.entity.vo.CommentLogVo;


public interface ILogDao
{
	int getCommentsCount(int busiId, String busiType);
	/**
	 * 获取CommentList
	 * @param busiId
	 * @param busiType
	 * @param newsFeedPageSize 
	 * @param start 
	 * @return
	 */
	List<CommentLogVo> getComment(int busiId, String busiType, int start, int newsFeedPageSize);
	/**
	 * 获取赞统计
	 * @param busiId
	 * @param busiType
	 * @return
	 */
	int getFavourCount(int busiId, String busiType);
	/**
	 * 点赞
	 * @param favourLog
	 * @return
	 */
	int favourIt(FavourLog favourLog);
	/**
	 * 获取评论数量
	 * @param busiId
	 * @param busiType
	 * @return
	 */
	int getCommentCount(int busiId, String busiType);
	/**
	 * 提交评论
	 * @param commentLog
	 * @return
	 */
	int commentIt(CommentLog commentLog);
	
	
}
