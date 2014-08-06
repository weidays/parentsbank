package com.weidays.parentsbank.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weidays.parentsbank.server.dao.IBankOwnerDao;
import com.weidays.parentsbank.server.entity.po.BankOwner;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.IBankOwnerService;

@Service
public class BankOwnerService implements IBankOwnerService{
	@Autowired
	private IBankOwnerDao bankOwnerDao;

	@Override
	public List saveOrUpdateBankOwner(BankOwner bankOwner) throws Exception {
		if(bankOwner.getId()==null){
			if(bankOwnerDao.checkAlready(bankOwner)){
				throw new Exception("用户名已存在");
			}else{
				List userList=bankOwnerDao.getUserDetailByHilifeId(bankOwnerDao.saveBankOwner(bankOwner));
				return userList;
			}
				
		}else{
			throw new Exception("有HILIFE_ID了，还来注册？");
		}
	}

	@Override
	public List getUserDetailByHilifeId(int hilifeId) throws Exception{
		
		
		return bankOwnerDao.getUserDetailByHilifeId(hilifeId);
	}

	@Override
	public BankOwner userLogin(BankOwner bankOwner) throws Exception{
		return bankOwnerDao.userLogin(bankOwner);
	}

	@Override
	public boolean updateBankOwner(BankOwner bankOwner, String updateContent) throws Exception{
		return bankOwnerDao.updateBankOwner(bankOwner,updateContent);
	}

}
