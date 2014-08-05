package com.weidays.parentsbank.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.IIndexDao;
import com.weidays.parentsbank.server.dao.ISharePicDao;
import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.AutoBoxingRowMapper;
import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.entity.vo.PictureShareVo;

@Repository
public class IndexDao extends BaseDao implements IIndexDao {


	@Override
	public List getNewsFeed(UserInfo userInfo,int start, int newsFeedPageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT A.SHARE_ID shareId,A.HILIFE_ID hilifeId,A.TITLE title,A.CONTENT content,A.SEND_TIME sendTime ,A.SEND_CITY sendCity,A.SENT_LOCATION sentLocation,A.LONGITUDE longitude,A.LATITUDE latitude,A.SOURCE source,A.FROM_SHARE_ID fromShareId ,B.NICKNAME nickname FROM PICTURE_SHARE A,USER_INFO B  WHERE A.HILIFE_ID=B.HILIFE_ID AND B.AREA_CODE=? LIMIT ?,?");
		List<Object> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] {userInfo.getAreaCode(),start,newsFeedPageSize},
				new AutoBoxingRowMapper(PictureShareVo.class, false));
		return list;
	}

	@Override
	public List getPicUrls(Integer shareId) {
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT B.PIC_URL FROM  PIC_SHARE_REL A,PIC_INFO B WHERE A.PIC_ID=B.PIC_ID AND A.SHARE_ID=?");
		List<Integer> list = this.getJdbcTemplate().queryForList(sb.toString(),
				new Object[] { shareId });
		List newList=new ArrayList();
		for (Object object : list) {
			ListOrderedMap map=(ListOrderedMap) object;
			newList.add(map.get("PIC_URL"));
		}
		return newList;
	}

	@Override
	public int getNewsFeedCount(UserInfo userInfo) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) FROM PICTURE_SHARE A,USER_INFO B  WHERE A.HILIFE_ID=B.HILIFE_ID AND B.AREA_CODE=? ");
		return this.getJdbcTemplate().queryForInt(sb.toString(),new Object[] {userInfo.getAreaCode()});
	}

	

}
