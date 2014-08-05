package com.weidays.parentsbank.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface ICommonService{
	public String getParaByCode(String paraCode);
	
}
