package com.weidays.parentsbank.server.dao;

import java.util.List;

import com.weidays.parentsbank.server.entity.po.BankOwner;


public interface IBankOwnerDao
{

	/**
	 * 保存用户
	 * @param bankOwner
	 * @return
	 */
	int saveBankOwner(BankOwner bankOwner)throws Exception;
	/**\
	 * 更新用户
	 * @param bankOwner
	 * @return
	 */
	boolean uploadBankOwner(BankOwner bankOwner)throws Exception;
	/**
	 * 通过ID拿到用户信息
	 * @param hilifeId
	 * @return
	 */
	List getUserDetailByHilifeId(int hilifeId)throws Exception;
	/**
	 * 用户登录
	 * @param bankOwner
	 * @return
	 */
	BankOwner userLogin(BankOwner bankOwner)throws Exception;
	/**
	 * 更新用户指定字段内容
	 * @param bankOwner
	 * @param updateContent
	 * @return
	 */
	boolean updateBankOwner(BankOwner bankOwner, String updateContent)throws Exception;
	/**
	 * 检查是否已经纯在用户名
	 * @param bankOwner
	 * @return
	 */
	boolean checkAlready(BankOwner bankOwner)throws Exception;
	
}
