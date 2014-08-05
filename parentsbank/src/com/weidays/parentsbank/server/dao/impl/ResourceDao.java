package com.weidays.parentsbank.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.IResourceDao;
import com.weidays.parentsbank.server.entity.po.PicInfo;

@Repository
public class ResourceDao extends BaseDao implements IResourceDao{

	
	@Override
	public int savePicInfo(PicInfo picInfo) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO PIC_INFO(HILIFE_ID,PIC_NAME,PIC_URL,UPLOAD_TIME) ");
		sb.append("VALUES(?,?,?,?)                             ");
		String lastId="SELECT MAX(PIC_ID) FROM PIC_INFO";
		try {
			updateBysql(
					sb.toString(),
					new Object[] {picInfo.getHilifeId(),picInfo.getPicName(),picInfo.getPicUrl(),System.currentTimeMillis()});
			return getJdbcTemplate().queryForInt(lastId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	

}
