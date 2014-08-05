package com.weidays.parentsbank.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.ISharePicDao;
import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.AutoBoxingRowMapper;
import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.entity.vo.PictureShareVo;

@Repository
public class SharePicDao extends BaseDao implements ISharePicDao {

	@Override
	public int savePicShare(PictureShareBo picShare) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO PICTURE_SHARE(HILIFE_ID,TITLE,CONTENT,SEND_TIME,SEND_CITY,SENT_LOCATION,LONGITUDE,LATITUDE,SOURCE)   ");
		sb.append("VALUES(?,?,?,?,?,?,?,?,'01')                             ");
		String lastId = "SELECT MAX(SHARE_ID) FROM PICTURE_SHARE";
		try {
			System.out.println(picShare.getContent()
					+ "#############################");
			updateBysql(sb.toString(),
					new Object[] { picShare.getHilifeId(), picShare.getTitle(),
							picShare.getContent(), 
							System.currentTimeMillis(),
							picShare.getSendCity(),
							
							picShare.getSentLocation(),
							picShare.getLongitude(), picShare.getLatitude() });
			return getJdbcTemplate().queryForInt(lastId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean savePicShareRel(Integer shareId, Integer picId) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO PIC_SHARE_REL(PIC_ID,SHARE_ID) VALUES(?,?)");
		try {
			updateBysql(sb.toString(), new Object[] { picId, shareId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delPicShare(Integer shareId) {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM  PICTURE_SHARE WHERE SHARE_ID=?");
		try {
			updateBysql(sb.toString(), new Object[] { shareId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delPicShareRel(Integer shareId) {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM  PIC_SHARE_REL WHERE SHARE_ID=?");
		try {
			updateBysql(sb.toString(), new Object[] { shareId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List getShareDetailByShareId(Integer shareId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT SHARE_ID shareId,HILIFE_ID hilifeId,TITLE title,CONTENT content,SEND_TIME sendTime ,SEND_CITY sendCity,SENT_LOCATION sentLocation,LONGITUDE longitude,LATITUDE latitude,SOURCE source,FROM_SHARE_ID fromShareId FROM PICTURE_SHARE WHERE SHARE_ID=? ");
		List<Object> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] { shareId },
				new AutoBoxingRowMapper(PictureShare.class, false));
		return list;
	}

	@Override
	public List<Integer> getSharePicRelByShareId(Integer shareId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT PIC_ID FROM  PIC_SHARE_REL WHERE SHARE_ID=?");
		List list = this.getJdbcTemplate().queryForList(sb.toString(),
				new Object[] { shareId });

		List<Integer> newList = new ArrayList<Integer>();
		for (Object object : list) {
			ListOrderedMap map = (ListOrderedMap) object;
			newList.add(Integer.parseInt(map.get("PIC_ID").toString()));

		}
		return newList;
	}

	@Override
	public int copyFromReship(PictureShareBo picShare) {
		//TODO 这里还有问题
//		StringBuffer sb = new StringBuffer();
//		sb.append("INSERT INTO PICTURE_SHARE(HILIFE_ID,TITLE,CONTENT,SEND_TIME,SEND_CITY,SENT_LOCATION,LONGITUDE,LATITUDE,SOURCE,FROM_SHARE_ID)   ");
//		sb.append("SELECT '"
//				+ picShare.getHilifeId()
//				+ "',TITLE,CONTENT,sysdate(),SEND_CITY,SENT_LOCATION,LONGITUDE,LATITUDE,'02','"
//				+ picShare.getFromShareId() + "'  ");
//		sb.append(" FROM PICTURE_SHARE WHERE SHARE_ID="
//				+ picShare.getFromShareId() + " ");
//		String lastId = "SELECT MAX(SHARE_ID) FROM PICTURE_SHARE";
//		try {
//			updateBysql(sb.toString(),
//					new Object[] { picShare.getHilifeId(), picShare.getTitle(),
//							picShare.getContent(), picShare.getSendCity(),
//							picShare.getSentLocation(),
//							picShare.getLongitude(), picShare.getLatitude() });
//			return getJdbcTemplate().queryForInt(lastId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return 0;
	}

	@Override
	public void saveNewPicShareRel(int newShareId, int fromShareId) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO PIC_SHARE_REL(PIC_ID,SHARE_ID)   ");
		sb.append("SELECT PIC_ID,'" + newShareId
				+ "' FROM  PIC_SHARE_REL WHERE SHARE_ID=?");
		try {
			updateBysql(sb.toString(), new Object[] { fromShareId });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List getNewsFeed(Integer hilifeId, int start, int newsFeedPageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT A.SHARE_ID shareId,A.HILIFE_ID hilifeId,A.TITLE title,A.CONTENT content,A.SEND_TIME sendTime ,A.SEND_CITY sendCity,A.SENT_LOCATION sentLocation,A.LONGITUDE longitude,A.LATITUDE latitude,A.SOURCE source,A.FROM_SHARE_ID fromShareId ,B.NICKNAME nickname FROM PICTURE_SHARE A,USER_INFO B  WHERE A.HILIFE_ID=B.HILIFE_ID AND  A.HILIFE_ID=? ORDER BY A.SEND_TIME LIMIT ?,?");
		List<Object> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] { hilifeId, start, newsFeedPageSize },
				new AutoBoxingRowMapper(PictureShareVo.class, false));
		return list;
	}

	@Override
	public List<String> getPicUrls(Integer shareId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT B.PIC_URL FROM  PIC_SHARE_REL A,PIC_INFO B WHERE A.PIC_ID=B.PIC_ID AND A.SHARE_ID=?");
		List list = this.getJdbcTemplate().queryForList(sb.toString(),
				new Object[] { shareId });
		List newList = new ArrayList();
		for (Object object : list) {
			ListOrderedMap map = (ListOrderedMap) object;
			newList.add(map.get("PIC_URL"));

		}
		return newList;
	}

	@Override
	public int getNewsFeedCount(Integer hilifeId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) FROM PICTURE_SHARE A,USER_INFO B  WHERE A.HILIFE_ID=B.HILIFE_ID AND  A.HILIFE_ID=? ");
		return this.getJdbcTemplate().queryForInt(sb.toString(),
				new Object[] { hilifeId });
	}

	@Override
	public List getAtMe(Integer hilifeId, int start, int newsFeedPageSize) {
		StringBuffer sb = new StringBuffer();
		// sb.append("SELECT A.SHARE_ID shareId,A.HILIFE_ID hilifeId,A.TITLE title,A.CONTENT content,A.SEND_TIME sendTime ,A.SEND_CITY sendCity,A.SENT_LOCATION sentLocation,A.LONGITUDE longitude,A.LATITUDE latitude,A.SOURCE source,A.FROM_SHARE_ID fromShareId ,B.NICKNAME nickname FROM PICTURE_SHARE A,USER_INFO B  WHERE A.HILIFE_ID=B.HILIFE_ID AND  A.HILIFE_ID=? LIMIT ?,?");
		sb.append(" SELECT * FROM                                                            ");
		sb.append(" (SELECT A.SHARE_ID      shareId,                                         ");
		sb.append("        A.HILIFE_ID     hilifeId,                                         ");
		sb.append("        A.TITLE         title,                                            ");
		sb.append("        A.CONTENT       content,                                          ");
		sb.append("        A.SEND_TIME     sendTime,                                         ");
		sb.append("        A.SEND_CITY     sendCity,                                         ");
		sb.append("        A.SENT_LOCATION sentLocation,                                     ");
		sb.append("        A.LONGITUDE     longitude,                                        ");
		sb.append("        A.LATITUDE      latitude,                                         ");
		sb.append("        A.SOURCE        source,                                           ");
		sb.append("        A.FROM_SHARE_ID fromShareId,                                      ");
		sb.append("        B.NICKNAME      nickname                                          ");
		sb.append("   FROM PICTURE_SHARE A, USER_INFO B                                      ");
		sb.append("  WHERE A.HILIFE_ID = B.HILIFE_ID                                         ");
		sb.append("    AND A.HILIFE_ID = ?                                                   ");
		sb.append(" UNION                                                                    ");
		sb.append(" SELECT DISTINCT A.SHARE_ID      shareId,                                 ");
		sb.append("                 A.HILIFE_ID     hilifeId,                                ");
		sb.append("                 A.TITLE         title,                                   ");
		sb.append("                 A.CONTENT       content,                                 ");
		sb.append("                 A.SEND_TIME     sendTime,                                ");
		sb.append("                 A.SEND_CITY     sendCity,                                ");
		sb.append("                 A.SENT_LOCATION sentLocation,                            ");
		sb.append("                 A.LONGITUDE     longitude,                               ");
		sb.append("                 A.LATITUDE      latitude,                                ");
		sb.append("                 A.SOURCE        source,                                  ");
		sb.append("                 A.FROM_SHARE_ID fromShareId,                             ");
		sb.append("                 B.NICKNAME      nickname                                 ");
		sb.append("   FROM PICTURE_SHARE A, USER_INFO B                                      ");
		sb.append("  WHERE A.HILIFE_ID = B.HILIFE_ID                                         ");
		sb.append("    AND A.SHARE_ID IN                                                     ");
		sb.append("        (SELECT DISTINCT BUSI_ID                                          ");
		sb.append("           FROM COMMENT_LOG C                                             ");
		sb.append("          WHERE C.HILIFE_ID = ? OR C.CONTENT LIKE '%@'||B.NICKNAME||' %') ");
		sb.append("     OR A.CONTENT LIKE '%@'||B.NICKNAME||' %'                             ");
		sb.append("     ) X ORDER BY X.sendTime DESC                                         ");
		sb.append("  LIMIT ?,?                                                             ");

		List<Object> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] { hilifeId, hilifeId, start, newsFeedPageSize },
				new AutoBoxingRowMapper(PictureShareVo.class, false));
		return list;
	}

	@Override
	public int getAtMeCount(Integer hilifeId) {
		StringBuffer sb = new StringBuffer();
		// sb.append("SELECT count(*) FROM PICTURE_SHARE A,USER_INFO B  WHERE A.HILIFE_ID=B.HILIFE_ID AND  A.HILIFE_ID=? ");
		sb.append(" SELECT COUNT(*) FROM (SELECT A.SHARE_ID      shareId,                      ");
		sb.append("        A.HILIFE_ID     hilifeId,                                           ");
		sb.append("        A.TITLE         title,                                              ");
		sb.append("        A.CONTENT       content,                                            ");
		sb.append("        A.SEND_TIME     sendTime,                                           ");
		sb.append("        A.SEND_CITY     sendCity,                                           ");
		sb.append("        A.SENT_LOCATION sentLocation,                                       ");
		sb.append("        A.LONGITUDE     longitude,                                          ");
		sb.append("        A.LATITUDE      latitude,                                           ");
		sb.append("        A.SOURCE        source,                                             ");
		sb.append("        A.FROM_SHARE_ID fromShareId,                                        ");
		sb.append("        B.NICKNAME      nickname                                            ");
		sb.append("   FROM PICTURE_SHARE A, USER_INFO B                                        ");
		sb.append("  WHERE A.HILIFE_ID = B.HILIFE_ID                                           ");
		sb.append("    AND A.HILIFE_ID = ?                                                     ");
		sb.append(" UNION                                                                      ");
		sb.append(" SELECT DISTINCT A.SHARE_ID      shareId,                                   ");
		sb.append("                 A.HILIFE_ID     hilifeId,                                  ");
		sb.append("                 A.TITLE         title,                                     ");
		sb.append("                 A.CONTENT       content,                                   ");
		sb.append("                 A.SEND_TIME     sendTime,                                  ");
		sb.append("                 A.SEND_CITY     sendCity,                                  ");
		sb.append("                 A.SENT_LOCATION sentLocation,                              ");
		sb.append("                 A.LONGITUDE     longitude,                                 ");
		sb.append("                 A.LATITUDE      latitude,                                  ");
		sb.append("                 A.SOURCE        source,                                    ");
		sb.append("                 A.FROM_SHARE_ID fromShareId,                               ");
		sb.append("                 B.NICKNAME      nickname                                   ");
		sb.append("   FROM PICTURE_SHARE A, USER_INFO B                                        ");
		sb.append("  WHERE A.HILIFE_ID = B.HILIFE_ID                                           ");
		sb.append("    AND A.SHARE_ID IN                                                       ");
		sb.append("        (SELECT DISTINCT BUSI_ID                                            ");
		sb.append("           FROM COMMENT_LOG C                                               ");
		sb.append("          WHERE C.HILIFE_ID = ? OR C.CONTENT LIKE '%@'||B.NICKNAME||' %')   ");
		sb.append("     OR A.CONTENT LIKE '%@'||B.NICKNAME||' %'                               ");
		sb.append(" ) X                                                                        ");
		return this.getJdbcTemplate().queryForInt(sb.toString(),
				new Object[] { hilifeId, hilifeId });
	}

}
