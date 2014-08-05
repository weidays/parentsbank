package com.weidays.parentsbank.server.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weidays.parentsbank.server.dao.IAreaCodeDao;
import com.weidays.parentsbank.server.dao.IUserInfoDao;
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.service.IAreaCodeService;
import com.weidays.parentsbank.server.service.IUserInfoService;

@Service
public class AreaCodeService implements IAreaCodeService{
	@Autowired
	private IAreaCodeDao areaCodeDao;
	
	
	@Override
	public List getOpenedAreaList() {
		return areaCodeDao.getOpenedAreaList();
	}

}
