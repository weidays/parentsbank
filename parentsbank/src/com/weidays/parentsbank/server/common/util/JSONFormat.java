package com.weidays.parentsbank.server.common.util;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.omg.CORBA.SystemException;

public class JSONFormat
{

    /**
     * 将实现IObject接口的java集合转换为JSONArray对象
     * 
     * @param c
     * @return
     * @throws SystemException
     */
    public static JSONArray toJSONArray(Collection c) throws  JSONException
    {
        JSONArray jsonArray = new JSONArray();
        return jsonArray;
    }
}
