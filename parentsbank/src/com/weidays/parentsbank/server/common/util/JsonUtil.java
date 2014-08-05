package com.weidays.parentsbank.server.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weidays.parentsbank.server.entity.vo.JsonVo;

/**
 * <p>
 * Title: JsonUtil
 * </p>
 * <p>
 * Description: JSON 工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014年4月15日
 * </p>
 * 
 * @author weidays
 * @version 1.0
 */
public class JsonUtil
{
	public static void printJsonVo(JsonVo jv,HttpServletResponse response) throws IOException{
		ObjectMapper mapper = JacksonMapper.getInstance();  
    	StringWriter sw = new StringWriter();  
    	JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);  
    	mapper.writeValue(gen, jv);  
    	gen.close();  
    	String json = sw.toString();  
    	ServletTool.print(json, response);
	}

}
