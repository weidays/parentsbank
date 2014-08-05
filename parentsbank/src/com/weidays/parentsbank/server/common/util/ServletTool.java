package com.weidays.parentsbank.server.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 通用的servlet工具
 * 
 * @author JerryLane
 */

public class ServletTool
{

    public static void info(String optdesc, String optresult, HttpServletRequest request)
    {
        MDC.put("username", request.getSession().getAttribute("username"));
        MDC.put("optobj", request.getRequestURI());
        MDC.put("optdesc", "");
        MDC.put("ipaddress", request.getRemoteAddr());
    }

    public static void print(String value, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();
        out.print(value);
        out.close();
    }

    public static void print(JSONObject jsonObj, HttpServletResponse response) throws IOException
    {
        print(jsonObj.toString(), response);
    }

    public static void print(JSONArray array, HttpServletResponse response) throws IOException
    {
        print(array.toString(), response);
    }

    public static void exportFile(String srcFile, String expName, HttpServletResponse response) throws Exception
    {
        byte[] buffer = new byte[1024];
        int bytes_read = 0;
        java.io.PrintStream ps = null;
        java.io.FileInputStream fis = null;
        try
        {
            ps = new java.io.PrintStream(response.getOutputStream());
            fis = new java.io.FileInputStream(srcFile);
            response.reset();
            expName = new String(expName.getBytes("GBK"), "iso8859_1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + expName + "\"");
            while ((bytes_read = fis.read(buffer)) > 0)
            {
                ps.write(buffer, 0, bytes_read);
            }
            ps.flush();
        }
        catch (Exception e)
        {
            throw new Exception("导出文件失败", e);
        }
        finally
        {
            try
            {
                fis.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                ps.close();
            }
            catch (Exception e)
            {
            }
        }
    }
}
