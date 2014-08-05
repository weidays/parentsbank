package com.weidays.parentsbank.server.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImgValidateCodeFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	public void destroy() {
		
	}

	public void doFilter(ServletRequest theRequest, ServletResponse theResponse,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) theRequest;
		HttpServletResponse response = (HttpServletResponse) theResponse;
		genernateValidateCodeImage(request, response);
	}

	private Color getRandColor(int fc,int bc){
	     Random random = new Random();
	     if(fc>255) fc=255;
	     if(bc>255) bc=255;
	     int r=fc+random.nextInt(bc-fc);
	     int g=fc+random.nextInt(bc-fc);
	     int b=fc+random.nextInt(bc-fc);
	     return new Color(r,g,b);
	}
	/**
	 * 生成验证码图
	 */
	protected void genernateValidateCodeImage(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {

		//Http 1.0 header
		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		//Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		try {
			 int width=60, height=20;
		     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		     Graphics g = image.getGraphics();
		     Random random = new Random();
		     g.setColor(getRandColor(200,250));
		     g.fillRect(0, 0, width, height);

		     g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		     g.setColor(getRandColor(160,200));
		     for (int i=0;i<155;i++)
		     {
		          int x = random.nextInt(width);
		          int y = random.nextInt(height);
		          int xl = random.nextInt(12);
		          int yl = random.nextInt(12);
		          g.drawLine(x,y,x+xl,y+yl);
		     }
		     String sRand="";
		     for (int i=0;i<4;i++){
		          String rand=String.valueOf(random.nextInt(10));
		          sRand+=rand;
		          g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
		          g.drawString(rand,13*i+6,16);
		     }
		    g.dispose();
		    ImageIO.write(image, "jpg", out);
		    request.getSession(true) .setAttribute("rand",sRand);
		     out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
}
