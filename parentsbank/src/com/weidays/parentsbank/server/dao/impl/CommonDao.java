package com.weidays.parentsbank.server.dao.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.ICommonDao;
@Repository
public class CommonDao extends BaseDao implements ICommonDao{
	public  String getParaByCode(String paraCode){
		String sql="SELECT para_value FROM para_config WHERE para_code=?";
			List list=getJdbcTemplate().queryForList(sql,new  Object[]{paraCode});
			if(list!=null&&list.size()>0){
				ListOrderedMap map=(ListOrderedMap) list.get(0);
				return (String) map.get("para_value");
			}else{
				return "";
			}
			
	}
	
}
