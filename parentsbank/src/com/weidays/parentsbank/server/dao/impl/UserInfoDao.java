package com.weidays.parentsbank.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.IUserInfoDao;
import com.weidays.parentsbank.server.entity.po.AutoBoxingRowMapper;
import com.weidays.parentsbank.server.entity.po.UserInfo;

@Repository
public class UserInfoDao extends BaseDao implements IUserInfoDao {

	@Override
	public int saveUserInfo(UserInfo userInfo) throws Exception{
		// ,HILIFE_ID,USER_NAME,NICKNAME,REAL_NAME,PASSWORD,GENDER,BIRTHDAY,CONSTELLATION,BLOOD_TYPE,EMAIL,PHONE_NUMBER,AVATAR,SAYING
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO USER_INFO(USER_NAME,NICKNAME,REAL_NAME,PASSWORD,GENDER,BIRTHDAY,");
		sb.append("CONSTELLATION,BLOOD_TYPE,EMAIL,PHONE_NUMBER,AVATAR,SAYING,RIGIST_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String lastId="SELECT MAX(HILIFE_ID) FROM USER_INFO";
		try {
			updateBysql(
					sb.toString(),
					new Object[] { userInfo.getUserName(),
							userInfo.getNickname(), userInfo.getRealName(),
							userInfo.getPassword(), userInfo.getGender(),
							userInfo.getBirthday(),
							userInfo.getConstellation(),
							userInfo.getBloodType(), userInfo.getEmail(),
							userInfo.getPhoneNumber(), userInfo.getAvatar(),
							userInfo.getSaying(),
							System.currentTimeMillis()});
			return getJdbcTemplate().queryForInt(lastId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存用户失败");
		}
	}

	@Override
	public boolean uploadUserInfo(UserInfo userInfo) throws Exception{
		// ,HILIFE_ID,USER_NAME,NICKNAME,REAL_NAME,PASSWORD,GENDER,BIRTHDAY,CONSTELLATION,BLOOD_TYPE,EMAIL,PHONE_NUMBER,AVATAR,SAYING
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE  USER_INFO SET USER_NAME=?,NICKNAME =?,REAL_NAME =?,PASSWORD=?,GENDER=?,BIRTHDAY=?,");
		sb.append("CONSTELLATION=?,BLOOD_TYPE=?,EMAIL=?,PHONE_NUMBER=?,AVATAR=?,SAYING=? WHERE HILIFE_ID=?");
		try {
			updateBysql(
					sb.toString(),
					new Object[] { userInfo.getUserName(),
							userInfo.getNickname(), userInfo.getRealName(),
							userInfo.getPassword(), userInfo.getGender(),
							userInfo.getBirthday(),
							userInfo.getConstellation(),
							userInfo.getBloodType(), userInfo.getEmail(),
							userInfo.getPhoneNumber(), userInfo.getAvatar(),
							userInfo.getSaying(), userInfo.getHilifeId() });
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List getUserDetailByHilifeId(int hilifeId) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT HILIFE_ID hilifeId,USER_NAME userName,NICKNAME nickname,REAL_NAME realName,PASSWORD password,GENDER gender,BIRTHDAY birthday,CONSTELLATION constellation ,BLOOD_TYPE bloodType,EMAIL email,PHONE_NUMBER phoneNumber,AVATAR avatar,SAYING saying ,AREA_CODE areaCode,RIGIST_TIME rigistTime  FROM USER_INFO WHERE HILIFE_ID=? ");
		List<Object> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] { hilifeId },
				new AutoBoxingRowMapper(UserInfo.class, false));
		return list;
	}

	@Override
	public UserInfo userLogin(UserInfo userInfo) throws Exception{
		String passwordMd5 = userInfo.getPassword();
		String sql = "SELECT HILIFE_ID hilifeId,USER_NAME userName,NICKNAME nickname,REAL_NAME realName,PASSWORD password,GENDER gender,BIRTHDAY birthday,CONSTELLATION constellation ,BLOOD_TYPE bloodType,EMAIL email,PHONE_NUMBER phoneNumber,AVATAR avatar,SAYING saying ,AREA_CODE areaCode FROM USER_INFO WHERE user_name=? AND PASSWORD =? ";
		// List list=this.getJdbcTemplate().queryForList(sql);
		List<Object> list = this.getJdbcTemplate().query(sql,
				new Object[] { userInfo.getUserName(), passwordMd5 },
				new AutoBoxingRowMapper(UserInfo.class, false));
		for (Object object : list) {
			if (object instanceof UserInfo) {
				UserInfo userInforet = (UserInfo) object;
				return userInforet;
			}
		}
		return null;
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo, String updateContent) throws Exception{
		// TODO Auto-generated method stub
		String something="";
		if(updateContent.equals("avatar")){
			something=" avatar='"+userInfo.getAvatar()+"' ";
		}else if(updateContent.equals("nickname")){
			something=" NICKNAME='"+userInfo.getNickname()+"' ";
		}else if(updateContent.equals("saying")){
			something=" SAYING='"+userInfo.getSaying()+"' ";
		}else if(updateContent.equals("bgImage")){
			something=" BG_IMAGE='"+userInfo.getBgImage()+"' ";
		}else if(updateContent.equals("bloodType")){
			something=" BLOOD_TYPE='"+userInfo.getBloodType()+"' ";
		}else if(updateContent.equals("email")){
			something=" EMAIL='"+userInfo.getEmail()+"' ";
		}else if(updateContent.equals("phoneNumber")){
			something=" PHONE_NUMBER='"+userInfo.getPhoneNumber()+"' ";
		}else if(updateContent.equals("birthday")){
			//TODO 此处决定生日传过来是什么格式.然后转换为时间类型再存
			something=" birthday='"+userInfo.getBirthday()+"' ";
		}else if(updateContent.equals("areaCode")){
			something=" AREA_CODE='"+userInfo.getAreaCode()+"' ";
		}
		String sql="UPDATE USER_INFO SET "+something+" WHERE  hilife_id ="+userInfo.getHilifeId()+"";
		try {
			this.getJdbcTemplate().execute(sql);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean checkAlready(UserInfo userInfo) throws Exception{
		String sql = "SELECT COUNT(1) FROM USER_INFO WHERE user_name=? ";
		
		try {
			int x= getJdbcTemplate().queryForInt(
					sql,
					new Object[] {userInfo.getUserName()});
			if(x>0){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		
	}

}
