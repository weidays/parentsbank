package com.weidays.parentsbank.server.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
public interface ICommonDao{
	public  String getParaByCode(String paraCode);
	
}
