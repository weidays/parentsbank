package com.weidays.parentsbank.server.common.util;

public class Utils
{
    /**
     * 如果s为null或者""返回true
     * @param s
     * @return
     */
public static boolean isNull(String s){
    if (s==null||"".equals(s)||"null".equals(s))
    {
       return true; 
    }
    return false; 
}

/** 
 * 删除input字符串中的html格式 
 *  
 * @param input 
 * @param length 
 * @return 
 */  
public static String splitAndFilterString(String input, int length) {  
    if (input == null || input.trim().equals("")) {  
         return "";  
     }  
     // 去掉所有html元素,  
     String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(  
             "<[^>]*>", "");  
     str = str.replaceAll("[(/>)<]", "");  
     int len = str.length();  
     if (len <= length) {  
         return str;  
     } else {  
         str = str.substring(0, length);  
         str += "......";  
     }  
     return str;  
 } 
}
