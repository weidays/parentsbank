package com.weidays.parentsbank.server.service;

import java.util.List;

import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.entity.vo.StateVo;

public interface IUserInfoService{
	
	/**
	 * 新增或更新用户信息
	 * @param userInfo
	 * @return
	 */
	public List saveOrUpdateUserInfo(UserInfo userInfo) throws Exception;
	/**
	 * 通过hilifeId拿到详细用户信息
	 * @param hilifeId
	 * @return
	 */
	public List getUserDetailByHilifeId(int hilifeId) throws Exception;
	/**
	 * 用户登录
	 * @param userInfo
	 * @return
	 */
	public UserInfo userLogin(UserInfo userInfo) throws Exception;
	/**
	 * 更新用户指定信息
	 * @param userInfo
	 * @param updateContent
	 * @return
	 */
	public boolean updateUserInfo(UserInfo userInfo, String updateContent)throws Exception;
	

}
