package com.weidays.parentsbank.server.service;

import java.util.List;

import com.weidays.parentsbank.server.entity.po.BankOwner;
import com.weidays.parentsbank.server.entity.vo.StateVo;

public interface IBankOwnerService{
	
	/**
	 * 新增或更新用户信息
	 * @param bankOwner
	 * @return
	 */
	public List saveOrUpdateBankOwner(BankOwner bankOwner) throws Exception;
	/**
	 * 通过hilifeId拿到详细用户信息
	 * @param hilifeId
	 * @return
	 */
	public List getUserDetailByHilifeId(int hilifeId) throws Exception;
	/**
	 * 用户登录
	 * @param bankOwner
	 * @return
	 */
	public BankOwner userLogin(BankOwner bankOwner) throws Exception;
	/**
	 * 更新用户指定信息
	 * @param bankOwner
	 * @param updateContent
	 * @return
	 */
	public boolean updateBankOwner(BankOwner bankOwner, String updateContent)throws Exception;
	

}
