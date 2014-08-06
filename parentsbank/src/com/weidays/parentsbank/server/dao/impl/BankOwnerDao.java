package com.weidays.parentsbank.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.IBankOwnerDao;
import com.weidays.parentsbank.server.entity.po.AutoBoxingRowMapper;
import com.weidays.parentsbank.server.entity.po.BankOwner;

@Repository
public class BankOwnerDao extends BaseDao implements IBankOwnerDao {

	@Override
	public int saveBankOwner(BankOwner bankOwner) throws Exception{
		// ,HILIFE_ID,USER_NAME,NICKNAME,REAL_NAME,PASSWORD,GENDER,BIRTHDAY,CONSTELLATION,BLOOD_TYPE,EMAIL,PHONE_NUMBER,AVATAR,SAYING
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO BANKOWNERS(USER_NAME,NICKNAME,REAL_NAME,PASSWORD,GENDER,BIRTHDAY,");
		sb.append("EMAIL,PHONE_NUMBER,AVATAR,RIGIST_TIME) VALUES (?,?,?,?,?,?,?,?,?,?)");
		String lastId="SELECT MAX(id) FROM USER_INFO";
		try {
			updateBysql(
					sb.toString(),
					new Object[] { bankOwner.getUserName(),
							bankOwner.getNickname(), bankOwner.getRealName(),
							bankOwner.getPassword(), bankOwner.getGender(),
							bankOwner.getBirthday(),
							 bankOwner.getEmail(),
							bankOwner.getPhoneNumber(), bankOwner.getAvatar(),
							System.currentTimeMillis()});
			return getJdbcTemplate().queryForInt(lastId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存用户失败");
		}
	}

	@Override
	public boolean uploadBankOwner(BankOwner bankOwner) throws Exception{
		// ,HILIFE_ID,USER_NAME,NICKNAME,REAL_NAME,PASSWORD,GENDER,BIRTHDAY,CONSTELLATION,BLOOD_TYPE,EMAIL,PHONE_NUMBER,AVATAR,SAYING
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE  bankowners SET USER_NAME=?,NICKNAME =?,REAL_NAME =?,PASSWORD=?,GENDER=?,BIRTHDAY=?,");
		sb.append("EMAIL=?,PHONE_NUMBER=?,AVATAR=? WHERE id=?");
		try {
			updateBysql(
					sb.toString(),
					new Object[] { bankOwner.getUserName(),
							bankOwner.getNickname(), bankOwner.getRealName(),
							bankOwner.getPassword(), bankOwner.getGender(),
							bankOwner.getBirthday(),
							bankOwner.getEmail(),
							bankOwner.getPhoneNumber(), bankOwner.getAvatar(),
							bankOwner.getId() });
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List getUserDetailByHilifeId(int hilifeId) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT id id,USER_NAME userName,NICKNAME nickname,REAL_NAME realName,PASSWORD password,GENDER gender,BIRTHDAY birthday,EMAIL email,PHONE_NUMBER phoneNumber,AVATAR avatar,RIGIST_TIME rigistTime  FROM bankowners WHERE id=? ");
		List<Object> list = this.getJdbcTemplate().query(sb.toString(),
				new Object[] { hilifeId },
				new AutoBoxingRowMapper(BankOwner.class, false));
		return list;
	}

	@Override
	public BankOwner userLogin(BankOwner bankOwner) throws Exception{
		String passwordMd5 = bankOwner.getPassword();
		String sql = "SELECT id id,USER_NAME userName,NICKNAME nickname,REAL_NAME realName,PASSWORD password,GENDER gender,BIRTHDAY birthday,EMAIL email,PHONE_NUMBER phoneNumber,AVATAR avatar FROM bankowners WHERE user_name=? AND PASSWORD =? ";
		// List list=this.getJdbcTemplate().queryForList(sql);
		List<Object> list = this.getJdbcTemplate().query(sql,
				new Object[] { bankOwner.getUserName(), passwordMd5 },
				new AutoBoxingRowMapper(BankOwner.class, false));
		for (Object object : list) {
			if (object instanceof BankOwner) {
				BankOwner bankOwnerret = (BankOwner) object;
				return bankOwnerret;
			}
		}
		return null;
	}

	@Override
	public boolean updateBankOwner(BankOwner bankOwner, String updateContent) throws Exception{
		// TODO Auto-generated method stub
		String something="";
		if(updateContent.equals("avatar")){
			something=" avatar='"+bankOwner.getAvatar()+"' ";
		}else if(updateContent.equals("nickname")){
			something=" NICKNAME='"+bankOwner.getNickname()+"' ";
		}else if(updateContent.equals("email")){
			something=" EMAIL='"+bankOwner.getEmail()+"' ";
		}else if(updateContent.equals("phoneNumber")){
			something=" PHONE_NUMBER='"+bankOwner.getPhoneNumber()+"' ";
		}else if(updateContent.equals("birthday")){
			//TODO 此处决定生日传过来是什么格式.然后转换为时间类型再存
			something=" birthday='"+bankOwner.getBirthday()+"' ";
		}
		String sql="UPDATE bankowners SET "+something+" WHERE  hilife_id ="+bankOwner.getId()+"";
		try {
			this.getJdbcTemplate().execute(sql);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean checkAlready(BankOwner bankOwner) throws Exception{
		String sql = "SELECT COUNT(1) FROM bankowners WHERE user_name=? ";
		
		try {
			int x= getJdbcTemplate().queryForInt(
					sql,
					new Object[] {bankOwner.getUserName()});
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
