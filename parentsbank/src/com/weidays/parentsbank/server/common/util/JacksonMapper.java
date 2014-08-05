package com.weidays.parentsbank.server.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapper {  
	  
    /** 
     *  
     */  
    private static final ObjectMapper mapper = new ObjectMapper();  
  
    /** 
     *  
     */  
    private JacksonMapper() {  
  
    }  
  
    /** 
     *  
     * @return 
     */  
    public static ObjectMapper getInstance() {  
  
        return mapper;  
    }  
  
}  