package com.weidays.parentsbank.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.views.xslt.ArrayAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weidays.parentsbank.server.dao.ILogDao;
import com.weidays.parentsbank.server.dao.ISharePicDao;
import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.entity.vo.PictureShareVo;
import com.weidays.parentsbank.server.service.ISharePicService;

@Service
public class SharePicService implements ISharePicService {
	@Autowired
	private ISharePicDao sharePicDao;
	@Autowired
	private ILogDao logDao;

	@Transactional
	public boolean sharePic(PictureShareBo picShare) {
		
		// 1保存picshare
		int shareId = sharePicDao.savePicShare(picShare);

		// 2保存pic和share的关联关系
		// 解析picShare
		List<Integer> picIds = picShare.getPicIds();
		for (Integer picId : picIds) {
			sharePicDao.savePicShareRel(shareId, picId);
		}

		return true;
	}

	@Override
	@Transactional
	public boolean shareDel(PictureShareBo picShare) {
		if (sharePicDao.delPicShare(picShare.getShareId())
				&& sharePicDao.delPicShareRel(picShare.getShareId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PictureShareVo getShareDetail(Integer shareId) {
		PictureShareVo vo= new PictureShareVo();
		PictureShare x= new PictureShare();
		List<PictureShare> psList=sharePicDao.getShareDetailByShareId(shareId);
		if(psList!=null&&psList.size()>0){
			x=psList.get(0);
			List<Integer> picIds=sharePicDao.getSharePicRelByShareId(shareId);
			List<String> picUrls=sharePicDao.getPicUrls(shareId);
			vo.create(x,picIds,picUrls);
			return vo;
		}else{
			return null;
		}
		
	}

	@Override
	public boolean reshipPic(PictureShareBo picShare) {
		//1.复制 fromShareId 的内容到新的shareId
		int newShareId = sharePicDao.copyFromReship(picShare);
		//2.复制fromShareId 关联的picId到关系表。
		sharePicDao.saveNewPicShareRel(newShareId, picShare.getFromShareId());
		

		return true;
	}

	@Override
	public List<PictureShareVo> getNewsFeed(Integer hilifeId, int start, int newsFeedPageSize) {
		//1.拿出每个pictureShare
		List<PictureShareVo> list= sharePicDao.getNewsFeed( hilifeId,  start,  newsFeedPageSize);
		
		for (PictureShareVo pictureShareVo : list) {
			//2.为每个pic放上 评论  .01busiType 是指 图片分享
			List comments=logDao.getComment(pictureShareVo.getShareId(), "01", start, newsFeedPageSize);
			pictureShareVo.setComments(comments);
			//3.为每个pic 放上赞数
			pictureShareVo.setFavourCount(logDao.getFavourCount(pictureShareVo.getShareId(), "01"));
			//4.为每个picshare 放上图片url
			List<String> picUrls=sharePicDao.getPicUrls(pictureShareVo.getShareId());
			pictureShareVo.setPicUrls(picUrls);
		}
		return list;
		
		
		
	}

	@Override
	public int getNewsFeedCount(Integer hilifeId) {
		return sharePicDao.getNewsFeedCount(hilifeId);
	}
	@Override
	public List<PictureShareVo> getAtMe(Integer hilifeId, int start, int newsFeedPageSize) {
		//1.拿出每个pictureShare
		List<PictureShareVo> list= sharePicDao.getAtMe( hilifeId,  start,  newsFeedPageSize);
		
		for (PictureShareVo pictureShareVo : list) {
			//2.为每个pic放上 评论  .01busiType 是指 图片分享
			List comments=logDao.getComment(pictureShareVo.getShareId(), "01", start, newsFeedPageSize);
			pictureShareVo.setComments(comments);
			//3.为每个pic 放上赞数
			pictureShareVo.setFavourCount(logDao.getFavourCount(pictureShareVo.getShareId(), "01"));
			//4.为每个picshare 放上图片url
			List<String> picUrls=sharePicDao.getPicUrls(pictureShareVo.getShareId());
			pictureShareVo.setPicUrls(picUrls);
		}
		return list;
		
		
		
	}

	@Override
	public int getAtMeCount(Integer hilifeId) {
		return sharePicDao.getAtMeCount(hilifeId);
	}
}
