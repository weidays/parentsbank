package com.weidays.parentsbank.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.weidays.parentsbank.server.common.BaseDao;
import com.weidays.parentsbank.server.dao.IAreaCodeDao;
import com.weidays.parentsbank.server.entity.po.AreaCode;
import com.weidays.parentsbank.server.entity.po.AutoBoxingRowMapper;

@Repository
public class AreaCodeDao extends BaseDao implements IAreaCodeDao {


	@Override
	public List getOpenedAreaList() {
		StringBuffer bf = new StringBuffer();
		bf.append("SELECT AREA_CODE      areaCode,       ");
		bf.append("       area_name      areaName,       ");
		bf.append("       f_area_code    fAreaCode,      ");
		bf.append("       sort_area_name sortAreaName,   ");
		bf.append("       area_level     areaLevel,      ");
		bf.append("       remark         remark,         ");
		bf.append("       STATUS         status          ");
		bf.append("  FROM area_code                      ");
		bf.append(" WHERE STATUS = '01'                  ");
		bf.append(" LIMIT 0,10                  ");
		List<Object> list = this.getJdbcTemplate().query(bf.toString(),
				new AutoBoxingRowMapper(AreaCode.class, false));
		return list;
	}

	

}
