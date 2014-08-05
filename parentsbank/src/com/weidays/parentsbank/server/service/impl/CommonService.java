package com.weidays.parentsbank.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weidays.parentsbank.server.dao.ICommonDao;
import com.weidays.parentsbank.server.service.ICommonService;

@Service
public class CommonService implements ICommonService{
	@Autowired
	private ICommonDao commonDao;
	public String getParaByCode(String paraCode){
		return commonDao.getParaByCode(paraCode);
	}
	
}
