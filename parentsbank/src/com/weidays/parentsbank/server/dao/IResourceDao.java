package com.weidays.parentsbank.server.dao;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.PicInfo;


public interface IResourceDao
{

	
	/**
	 * 保存图片信息
	 * @param picInfo
	 * @return  picId
	 */
	int savePicInfo(PicInfo picInfo);
	
}
