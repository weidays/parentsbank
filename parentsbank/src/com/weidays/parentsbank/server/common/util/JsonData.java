package com.weidays.parentsbank.server.common.util;

import java.lang.reflect.Method;
import java.util.List;

public class JsonData
{

    public JsonData()
    {
    }

    public StringBuffer getJsonData(List<Object> voList, int start, int limit, String path)
    {
        StringBuffer jsonData = new StringBuffer();
        Object vo = null;
        int psize = limit + start > voList.size() ? voList.size() : limit + start;
        for (int i = start; i < psize; i++)
        {
            vo = voList.get(i);
            jsonData.append(parseVo(vo, path));
            if (i != psize - 1)
                jsonData.append(",");
        }

        return jsonData;
    }

    public StringBuffer getComboJsonData(List<Object> voList, String optionValue, String optionText, String path)
    {
        StringBuffer jsonData = new StringBuffer();
        Object vo = null;
        int psize = voList.size();
        if (psize > 0)
        {
            for (int i = 0; i < psize; i++)
            {
                vo = voList.get(i);
                jsonData.append(parseComboVo(vo, path, optionValue, optionText));
                if (i != psize - 1)
                    jsonData.append(",");
            }

        }
        return jsonData;
    }

    public StringBuffer getComboJsonData(List<Object> voList, String optionValue, String optionText, String reserve,
            String path)
    {
        StringBuffer jsonData = new StringBuffer();
        Object vo = null;
        int psize = voList.size();
        if (psize > 0)
        {
            for (int i = 0; i < psize; i++)
            {
                vo = voList.get(i);
                jsonData.append(parseComboVo(vo, path, optionValue, optionText, reserve));
                if (i != psize - 1)
                    jsonData.append(",");
            }

        }
        return jsonData;
    }


    private String parseComboVo(Object obj, String path, String optionValue, String optionText)
    {
        Class clazz = null;
        StringBuffer buff = new StringBuffer();
        try
        {
            if (obj.getClass().toString().equals(Class.forName(path).toString()))
            {
                clazz = obj.getClass();
                String optionValueMethodName = (new StringBuilder("get")).append(
                    optionValue.substring(0, 1).toUpperCase()).append(optionValue.substring(1)).toString();
                String optionTextMethodName = (new StringBuilder("get")).append(
                    optionText.substring(0, 1).toUpperCase()).append(optionText.substring(1)).toString();
                Method valueMethod = clazz.getMethod(optionValueMethodName, new Class[0]);
                Method textMethod = clazz.getMethod(optionTextMethodName, new Class[0]);
                String vlrvl = valueMethod.invoke(obj, new Object[0]).toString();
                String txrvl = textMethod.invoke(obj, new Object[0]).toString();
                buff.append((new StringBuilder("{retrunValue:'")).append(vlrvl).append("',displayText:'").append(txrvl)
                        .append("'}").toString());
            }
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }
        return buff.toString();
    }

    private String parseComboVo(Object obj, String path, String optionValue, String optionText, String reserve)
    {
        Class clazz = null;
        StringBuffer buff = new StringBuffer();
        try
        {
            if (obj.getClass().toString().equals(Class.forName(path).toString()))
            {
                clazz = obj.getClass();
                String optionValueMethodName = (new StringBuilder("get")).append(
                    optionValue.substring(0, 1).toUpperCase()).append(optionValue.substring(1)).toString();
                String optionTextMethodName = (new StringBuilder("get")).append(
                    optionText.substring(0, 1).toUpperCase()).append(optionText.substring(1)).toString();
                String reserveMethodName = (new StringBuilder("get")).append(reserve.substring(0, 1).toUpperCase())
                        .append(reserve.substring(1)).toString();
                Method valueMethod = clazz.getMethod(optionValueMethodName, new Class[0]);
                Method textMethod = clazz.getMethod(optionTextMethodName, new Class[0]);
                Method reserveMethod = clazz.getMethod(reserveMethodName, new Class[0]);
                String vlrvl = valueMethod.invoke(obj, new Object[0]).toString();
                String txrvl = textMethod.invoke(obj, new Object[0]).toString();
                String rervl = reserveMethod.invoke(obj, new Object[0]).toString();
                buff.append((new StringBuilder("{retrunValue:'")).append(vlrvl).append("',displayText:'").append(txrvl)
                        .append("',reserve:'").append(rervl).append("'}").toString());
            }
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }
        return buff.toString();
    }

    private String parseVo(Object obj, String path)
    {
        Class clazz = null;
        StringBuffer buff = new StringBuffer();
        String rev = null;
        try
        {
            if (obj.getClass().toString().equals(Class.forName(path).toString()))
            {
                buff.append("{");
                clazz = obj.getClass();
                Method declaredMethods[] = clazz.getDeclaredMethods();
                for (int i = 0; i < declaredMethods.length; i++)
                {
                    Method method = declaredMethods[i];
                    if (method.getName().indexOf("get") == 0)
                    {
                        Method domethod = clazz.getMethod(method.getName(), new Class[0]);
                        String field = method.getName().toString().substring(3).toLowerCase();
                        String rVal = domethod.invoke(obj, new Object[0]) != null ? String.valueOf(domethod.invoke(obj,
                            new Object[0])) : "";
                        rVal = rVal.replaceAll("\\\\", "\\\\\\\\");
                        rVal = rVal.replaceAll("\"", "\\\\\"");
                        rVal = rVal.replaceAll("\n", "");
                        rVal = rVal.replaceAll("\r", "");
                        buff.append((new StringBuilder("\"")).append(field).append("\":\"").append(rVal).append("\",")
                                .toString());
                    }
                }

                rev = (new StringBuilder(String.valueOf(buff.substring(0, buff.length() - 1)))).append("}").toString();
            }
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }
        return rev;
    }

}
