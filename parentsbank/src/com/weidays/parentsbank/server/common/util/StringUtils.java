package com.weidays.parentsbank.server.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils
{

    public StringUtils()
    {
    }

    public static String removalQuotes(String str)
    {
        if (str.startsWith("\"") || str.startsWith("'"))
            str = str.substring(1, str.length());
        if (str.endsWith("\"") || str.endsWith("'"))
            str = str.substring(0, str.length() - 1);
        return str;
    }

    public static String getDateFormat(String pattern)
    {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }
}
