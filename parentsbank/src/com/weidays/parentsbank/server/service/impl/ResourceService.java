package com.weidays.parentsbank.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weidays.parentsbank.server.dao.IResourceDao;
import com.weidays.parentsbank.server.entity.po.PicInfo;
import com.weidays.parentsbank.server.service.IResourceService;

@Service
public class ResourceService implements IResourceService {
	@Autowired
	private IResourceDao resourceDao;
    //hdfs路径
    private String hdfsPath;
    //Hadoop系统配置
    
	@Override
	public int savePicInfo(PicInfo picInfo) {
		
		return	resourceDao.savePicInfo(picInfo);
	}
	





}
