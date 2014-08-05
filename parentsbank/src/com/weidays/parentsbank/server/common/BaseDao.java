package com.weidays.parentsbank.server.common;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BaseDao extends JdbcDaoSupport {
	public int updateBysql(String sql, Object[] args){
		return getJdbcTemplate().update(sql, args);
		
	}
	public  String getParaByCode(String paraCode){
		String sql="SELECT para_value FROM para_config WHERE para_code=?";
			List list=getJdbcTemplate().queryForList(sql,new  Object[]{paraCode});
			if(list!=null&&list.size()>0){
				return (String) list.get(0);
			}else{
				return "";
			}
			
	}
	
}
