package com.weidays.parentsbank.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weidays.parentsbank.server.dao.IUserInfoDao;
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.IUserInfoService;

@Service
public class UserInfoService implements IUserInfoService{
	@Autowired
	private IUserInfoDao userInfoDao;

	@Override
	public List saveOrUpdateUserInfo(UserInfo userInfo) throws Exception {
		if(userInfo.getHilifeId()==null){
			if(userInfoDao.checkAlready(userInfo)){
				throw new Exception("用户名已存在");
			}else{
				List userList=userInfoDao.getUserDetailByHilifeId(userInfoDao.saveUserInfo(userInfo));
				return userList;
			}
				
		}else{
			throw new Exception("有HILIFE_ID了，还来注册？");
		}
	}

	@Override
	public List getUserDetailByHilifeId(int hilifeId) throws Exception{
		
		
		return userInfoDao.getUserDetailByHilifeId(hilifeId);
	}

	@Override
	public UserInfo userLogin(UserInfo userInfo) throws Exception{
		return userInfoDao.userLogin(userInfo);
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo, String updateContent) throws Exception{
		return userInfoDao.updateUserInfo(userInfo,updateContent);
	}

}
