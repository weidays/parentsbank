package com.weidays.parentsbank.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weidays.parentsbank.server.dao.IIndexDao;
import com.weidays.parentsbank.server.dao.ILogDao;
import com.weidays.parentsbank.server.dao.ISharePicDao;
import com.weidays.parentsbank.server.dao.IBankOwnerDao;
import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.PictureShare;
import com.weidays.parentsbank.server.entity.po.BankOwner;
import com.weidays.parentsbank.server.entity.vo.PictureShareVo;
import com.weidays.parentsbank.server.service.IIndexService;
import com.weidays.parentsbank.server.service.ISharePicService;

@Service
public class IndexService implements IIndexService {
	@Autowired
	private IIndexDao indexDao;
	@Autowired
	private ILogDao logDao;
	@Autowired
	private IBankOwnerDao userInfoDao;

	

}
