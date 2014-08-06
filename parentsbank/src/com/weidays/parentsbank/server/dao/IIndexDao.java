package com.weidays.parentsbank.server.dao;

import java.util.List;

import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.BankOwner;


public interface IIndexDao
{


	public List getPicUrls(Integer shareId);
	
}
