package com.weidays.parentsbank.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.ILogDao;
import com.weidays.parentsbank.server.entity.po.AutoBoxingRowMapper;
import com.weidays.parentsbank.server.entity.po.CommentLog;
import com.weidays.parentsbank.server.entity.po.FavourLog;
import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.entity.vo.CommentLogVo;

@Repository
public class LogDao extends BaseDao implements ILogDao {

	@Override
	public int getCommentsCount(int busiId, String busiType) {
		StringBuffer sb = new StringBuffer();
		//TODO 此处需要用到BO以及如何展示下级回复者
		sb.append("SELECT COUNT(1) FROM COMMENT_LOG A ,USER_INFO B   ");
		sb.append("WHERE A.P_COMMENT_ID=0 AND A.HILIFE_ID=B.HILIFE_ID AND  BUSI_ID=? AND BUSI_TYPE=?   ");
		try {
			return getJdbcTemplate().queryForInt(
					sb.toString(),
					new Object[] {busiId,busiType});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<CommentLogVo> getComment(int busiId, String busiType, int start, int newsFeedPageSize) {
		StringBuffer sb = new StringBuffer();
		//TODO 此处需要用到BO以及如何展示下级回复者
		sb.append("SELECT A.COMMENT_ID commentId,A.BUSI_ID busiId,A.P_COMMENT_ID PCommentId,");
		sb.append("A.HILIFE_ID hilifeId ,A.COMMENT_TIME commentTime ,A.CONTENT content");
		sb.append(",A.BUSI_TYPE busiType ,B.NICKNAME  nickname FROM COMMENT_LOG A ,USER_INFO B   ");
		sb.append("WHERE A.P_COMMENT_ID=0 AND A.HILIFE_ID=B.HILIFE_ID AND  BUSI_ID=? AND BUSI_TYPE=? LIMIT ?,?   ");
		List<CommentLogVo> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] { busiId,busiType,start,newsFeedPageSize },
				new AutoBoxingRowMapper(CommentLogVo.class, false));
		for (CommentLogVo commentLogVo : list) {
			
			//此List 用于存放所有的下级评论。
			List<CommentLogVo> allChildList=new ArrayList<CommentLogVo>();
			commentLogVo.setChildren(getUnderComments(commentLogVo.getCommentId(),allChildList));
		}
		return list;
	}
	//此处用递归
	public List<CommentLogVo> getUnderComments(int pLogId,List<CommentLogVo> allChildList){
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT A.COMMENT_ID commentId,A.BUSI_ID busiId,A.P_COMMENT_ID PCommentId,     ");
		sb.append("A.HILIFE_ID hilifeId ,A.COMMENT_TIME commentTime ,A.CONTENT content           ");
		sb.append(",A.BUSI_TYPE busiType ,B.NICKNAME  nickName FROM COMMENT_LOG A ,USER_INFO B   ");
		sb.append("WHERE A.P_COMMENT_ID=? AND A.HILIFE_ID=B.HILIFE_ID     ");
		List<CommentLogVo> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] {pLogId},
				new AutoBoxingRowMapper(CommentLogVo.class, false));
		allChildList.addAll(list);
		for (CommentLogVo commentLogVo : list) {
			//这样做就是很多层
			//commentLogVo.setChildren(getUnderComments(commentLogVo.getCommentId()));
			//这样做是保证只有两层
			getUnderComments(commentLogVo.getCommentId(),allChildList);
			
		}
		return allChildList;
	}

	@Override
	public int getFavourCount(int busiId, String busiType) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1) FROM FAVOUR_LOG WHERE BUSI_ID=? AND BUSI_TYPE=? ");
		try {
			return getJdbcTemplate().queryForInt(
					sb.toString(),
					new Object[] {busiId,busiType});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int favourIt(FavourLog favourLog) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO  FAVOUR_LOG(BUSI_ID,HILIFE_ID,FAVOUR_TIME,BUSI_TYPE)");
		sb.append("VALUES(?,?,?,?)                             ");
		try {
			updateBysql(
					sb.toString(),
					new Object[] {favourLog.getBusiId(),favourLog.getHilifeId(),System.currentTimeMillis(),favourLog.getBusiType()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getFavourCount(favourLog.getBusiId(),favourLog.getBusiType());
	}

	@Override
	public int getCommentCount(int busiId, String busiType) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1) FROM COMMENT_LOG WHERE BUSI_ID=? AND BUSI_TYPE=? ");
		try {
			return getJdbcTemplate().queryForInt(
					sb.toString(),
					new Object[] {busiId,busiType});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int commentIt(CommentLog commentLog) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO COMMENT_LOG(BUSI_ID,P_COMMENT_ID,HILIFE_ID,COMMENT_TIME,CONTENT,TOP_COMMENT_ID,BUSI_TYPE)");
		sb.append("VALUES(?,?,?,?,?,?,?)                             ");
		try {
			updateBysql(
					sb.toString(),
					new Object[] {commentLog.getBusiId(),
						commentLog.getPCommentId(),
						commentLog.getHilifeId(),
						System.currentTimeMillis(),
						commentLog.getContent(),
						commentLog.getTopCommentId(),
						commentLog.getBusiType()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



	

}
