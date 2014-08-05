package com.weidays.parentsbank.server.dao;

import java.util.List;

import com.weidays.parentsbank.server.entity.po.UserInfo;


public interface IUserInfoDao
{

	/**
	 * 保存用户
	 * @param userInfo
	 * @return
	 */
	int saveUserInfo(UserInfo userInfo)throws Exception;
	/**\
	 * 更新用户
	 * @param userInfo
	 * @return
	 */
	boolean uploadUserInfo(UserInfo userInfo)throws Exception;
	/**
	 * 通过ID拿到用户信息
	 * @param hilifeId
	 * @return
	 */
	List getUserDetailByHilifeId(int hilifeId)throws Exception;
	/**
	 * 用户登录
	 * @param userInfo
	 * @return
	 */
	UserInfo userLogin(UserInfo userInfo)throws Exception;
	/**
	 * 更新用户指定字段内容
	 * @param userInfo
	 * @param updateContent
	 * @return
	 */
	boolean updateUserInfo(UserInfo userInfo, String updateContent)throws Exception;
	/**
	 * 检查是否已经纯在用户名
	 * @param userInfo
	 * @return
	 */
	boolean checkAlready(UserInfo userInfo)throws Exception;
	
}
