package com.weidays.parentsbank.server.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonMethod
{

    private static final int DEF_DIV_SCALE = 10; // 默认除法运算精度

    /**
     * 拷贝目录 sourceFile 源目录 targetFile 目标目录
     */
    public static void copyDirectiory(String sourceDir, String targetDir)
    {
        try
        {
            File tFile = new File(targetDir);
            if (!tFile.exists())
            {
                tFile.mkdirs();
            }
            File sFile = new File(sourceDir);
            if (!sFile.exists())
            {
                return;
            }
            File[] files = sFile.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isFile())
                {
                    FileInputStream input = new FileInputStream(files[i]);
                    FileOutputStream output = new FileOutputStream(targetDir + File.separatorChar + files[i].getName());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1)
                    {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (files[i].isDirectory())
                {
                    copyDirectiory(sourceDir + File.separatorChar + files[i].getName(), targetDir + File.separatorChar
                            + files[i].getName());
                }
            }
        }
        catch (Exception ex)
        {
            ////System.out.println("将目录拷贝至目标目录出错");
            ex.printStackTrace();
        }
    }

    // 拷贝文件
    public static void copyFile(String sourceFile, String targetFile)
    {
        try
        {
            if (File.separatorChar == '\\')
            {
                targetFile = targetFile.replaceAll("/", "\\");
            }
            else
            {
                targetFile = targetFile.replaceAll("\\\\", "/");
            }
            FileInputStream input = new FileInputStream(sourceFile);
            FileOutputStream output = new FileOutputStream(targetFile);
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = input.read(b)) != -1)
            {
                output.write(b, 0, len);
            }
            output.flush();
            output.close();
            input.close();
        }
        catch (Exception ex)
        {
            ////System.out.println("拷贝文件出错");
            ex.printStackTrace();
        }
    }

    /** */
    /**
     * 提供精确的加法运算。
     * 
     * @param v1 被加数
     * @param v2加数
     */
    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     * 
     * @param v1 被减数
     * @param v2 减数
     */
    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     * 
     * @param v1 被乘数
     * @param v2乘数
     */
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     */
    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位
     */
    public static double div(double v1, double v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("精确位数须>=0");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * 
     * @param v 数字
     * @param scale 小数点后保留几位
     */
    public static double round(double v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("精确位数须>=0");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 将单位元(字符)转化为厘(整数)
    public static int formatAmount(String amount)
    {
        amount = amount.replaceAll(",", "");
        double value = Double.parseDouble(amount);
        value = mul(value, 1000);
        return (int) value;
    }

    // 将单位厘(整数)转化为元(字符)
    public static String formatAmount(int amount)
    {
        double value = div(amount, 1000, 3);
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(value);
    }

    public static String doubleToString(double value, String format)
    {
        if (format == null || "".equals(format))
        {
            format = "#.##";
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
    }

    public static String objToString(Object obj, String format)
    {
        if (format == null || "".equals(format))
        {
            format = "#.##";
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(obj);
    }

    /**
     * @param date
     * @param format yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToString(Date date, String format)
    {
        if (date != null)
        {
            if ("".equals(format))
            {
                format = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
        else
        {
            return "";
        }
    }

    public static String dateToString(Date date)
    {
        return dateToString(date, "yyyy-MM-dd");
    }

    /**
     * @param s
     * @param format yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date stringToDate(String s, String format)
    {
        try
        {
            if ((s != null) && (!s.equalsIgnoreCase("")))
            {
                if ("".equals(format))
                {
                    format = "yyyy-MM-dd HH:mm:ss";
                }
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
                java.util.Date birthday = sdf.parse(s);
                return birthday;
            }
            else
            {
                return null;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    // 判断开始时间和结束时间是否符合逻辑
    protected static boolean dateTimeCompare(String beginDate, String endDate, String format) throws Exception
    {
        if ("".equals(format))
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(beginDate).before(df.parse(endDate));
    }

    public static void main(String args[])
    {
        /*
         * double v1 = 123.456; double v2 = 123.890003;
         * ////System.out.println(add(v1, v2)); //System.out.println(sub(v2, v1));
         * //System.out.println(mul(v2, v1)); //System.out.println(round(mul(v2,
         * v1), 0)); //System.out.println(div(v2, v1, 3));
         * //System.out.println(round(v2, 4));
         * //System.out.println(formatAmount(123456789));
         * //System.out.println(formatAmount("2,123,456.7899"));
         * //System.out.println(Integer.MAX_VALUE);
         */
    }
}
