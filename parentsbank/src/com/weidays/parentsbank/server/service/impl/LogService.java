package com.weidays.parentsbank.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weidays.parentsbank.server.dao.ILogDao;
import com.weidays.parentsbank.server.dao.ISharePicDao;
import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.CommentLog;
import com.weidays.parentsbank.server.entity.po.FavourLog;
import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.service.ILogService;
import com.weidays.parentsbank.server.service.ISharePicService;

@Service
public class LogService implements ILogService {
	@Autowired
	private ILogDao logDao;

	@Override
	public List getComment(int busiId, String busiType, int start, int newsFeedPageSize) {
		
		return logDao.getComment(busiId,busiType,  start,  newsFeedPageSize);
	}

	@Override
	public int getCommentsCount(int busiId, String busiType) {
		// TODO Auto-generated method stub
		return logDao.getCommentsCount(busiId,busiType);
	}

	@Override
	public int getFavourCount(int busiId, String busiType) {
		return logDao.getFavourCount(busiId,busiType);
	}

	@Override
	public int favourIt(FavourLog favourLog) {
		return logDao.favourIt(favourLog);
	}

	@Override
	public int getCommentCount(int busiId, String busiType) {
		return logDao.getCommentCount(busiId,busiType);
		}

	@Override
	public int commentIt(CommentLog commentLog) {
		return logDao.commentIt(commentLog);
	}

	


}
