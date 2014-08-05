package com.weidays.parentsbank.server.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgCodeServlet extends HttpServlet
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String code = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++)
        {
            code += r.nextInt(10);
        }
        request.getSession().setAttribute("code", code);
        BufferedImage bimg = new BufferedImage(50, 22, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimg.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 70, 22);
        Font f = new Font("Times New Roman", Font.ITALIC, 18);
        g.setFont(f);
        for (int i = 0; i < code.length(); i++)
        {
            g.setColor(new Color(r.nextInt(225), r.nextInt(235), r.nextInt(251)));
            g.drawString(code.substring(i, i + 1), 4 + i * 10, 18);
        }
        // for (int i=0;i<155;i++) {
        // int x = r.nextInt(70);
        // int y = r.nextInt(22);
        // g.drawLine(x,y,x,y);
        // }
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bimg);
        out.flush();
    }
}
