package com.weidays.parentsbank.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weidays.parentsbank.server.dao.IIndexDao;
import com.weidays.parentsbank.server.dao.ILogDao;
import com.weidays.parentsbank.server.dao.ISharePicDao;
import com.weidays.parentsbank.server.dao.IUserInfoDao;
import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.entity.vo.PictureShareVo;
import com.weidays.parentsbank.server.service.IIndexService;
import com.weidays.parentsbank.server.service.ISharePicService;

@Service
public class IndexService implements IIndexService {
	@Autowired
	private IIndexDao indexDao;
	@Autowired
	private ILogDao logDao;
	@Autowired
	private IUserInfoDao userInfoDao;

	@Override
	public List<PictureShareVo> getNewsFeed(UserInfo userInfo,int start, int newsFeedPageSize) throws Exception {
		//1.拿出每个pictureShare
		
		List<PictureShareVo> list= indexDao.getNewsFeed(userInfo,start, newsFeedPageSize);
		
		for (PictureShareVo pictureShareVo : list) {
			//2.为每个pic放上 评论  .01busiType 是指 图片分享
			//List comments=logDao.getComment(pictureShareVo.getShareId(), "01", start, newsFeedPageSize);
			pictureShareVo.setCommentsCount(logDao.getCommentCount(pictureShareVo.getShareId(), "01"));
			//pictureShareVo.setComments(comments);
			//3.为每个pic 放上赞数
			pictureShareVo.setFavourCount(logDao.getFavourCount(pictureShareVo.getShareId(), "01"));
			//4.为每个picshare 放上图片url
			List picUrls=indexDao.getPicUrls(pictureShareVo.getShareId());
			
			List<UserInfo> l=userInfoDao.getUserDetailByHilifeId(pictureShareVo.getHilifeId());
			if(l!=null&&l.size()>0){
				pictureShareVo.setUserInfo(l.get(0));
			}
			pictureShareVo.setPicUrls(picUrls);
		}
		return list;
		
		
		
	}

	@Override
	public int getNewsFeedCount(UserInfo userInfo) {
		return indexDao.getNewsFeedCount(userInfo);
	}

}
