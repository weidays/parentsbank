package com.weidays.parentsbank.server.service;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.PicInfo;

public interface IResourceService{
	
	/**
	 * 保存图片信息
	 * @param picInfo
	 * @return
	 */

	public int savePicInfo(PicInfo picInfo);
	

}
