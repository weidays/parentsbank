package com.weidays.parentsbank.server.common;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.weidays.parentsbank.server.common.util.JsonData;
import com.weidays.parentsbank.server.common.util.StringUtils;
import com.weidays.parentsbank.server.entity.po.PicInfo;
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.service.ICommonService;
import com.weidays.parentsbank.server.service.IResourceService;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = -768164344249793369L;
    public static int STATUS_SUCCESS=0;
    public static int STATUS_FAILED=1;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext context;

	@Autowired
	protected ICommonService commonSevice;

	public boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public Map getApplication() {
		return ServletActionContext.getContext().getApplication();
	}

	/**
	 * @param date
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public String dateToString(Date date, String format) {
		if (date != null) {
			if ("".equals(format)) {
				format = "yyyy-MM-dd HH:mm:ss";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * @param s
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public Date stringToDate(String s, String format) {
		try {
			if ((s != null) && (!s.equalsIgnoreCase(""))) {
				if ("".equals(format)) {
					format = "yyyy-MM-dd HH:mm:ss";
				}
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						format);
				java.util.Date birthday = sdf.parse(s);
				return birthday;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// 判断开始时间和结束时间是否符合逻辑
	protected boolean dateTimeCompare(String beginDate, String endDate,
			String format) throws Exception {
		if ("".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat df = new SimpleDateFormat(format);
		return df.parse(beginDate).before(df.parse(endDate));
	}

	



	
	

	// 格式化参数 &param=value&..
	protected HashMap parseParams(String params) throws Exception {
		HashMap map = null;
		if (params == null || "".equals(params)) {
			return null;
		}
		String[] temp = params.split("&");
		if (temp.length > 0) {
			map = new HashMap();
			for (int i = 0; i < temp.length; i++) {
				String[] param = temp[i].split("=");
				String key = param[0];
				if (!"".equals(key)) {
					if (param.length == 2) {
						map.put(key, "null".equals(param[1]) ? null : param[1]);
					} else {
						map.put(key, null);
					}
				}
			}
		}

		return map;
	}

	public String formatMsg(String msg) {
		if (msg != null && !"".equals(msg)) {
			msg = msg.replaceAll("\\\\", "\\\\\\\\");
			msg = msg.replaceAll("\"", "\\\\\"");
			msg = msg.replaceAll("\n", "");
			msg = msg.replaceAll("\r", "");
			msg = msg.replaceAll(":", " ");
			return msg;
		}
		return "";
	}

	/**
	 * 去掉查询条件中的 ' ; - 字符 防止sql注入
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceSql(String str) {
		String[] sqls = { "'", "-", ";" };
		for (String s : sqls) {
			str = str.replace(s, "");
		}
		return str;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.ServletContextAware#setServletContext(javax.servlet
	 * .ServletContext)
	 */
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletResponseAware#setServletResponse
	 * (javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	/**
	 * 资源ACtion
	 */
	// private static final long serialVersionUID = -8520183575801275576L;
	// 上传文件域
	private File image[];
	private String imageContentType[];
	private String imageFileName[];
	// 接受依赖注入的属性
	private String savePath;

	private UserInfo userInfo;
	@Autowired
	private IResourceService resourceService;

	/**
	 * 上传资源的接口</br> 要求：1.File 的Key值为 “image”</br> 2.其他信息包括图片名称 等Key值 存放在 picInfo
	 * 对象下面 如：picInfo.picName</br> 3.将会返回picId</br> 4.上传路径请在struts-resource
	 * 中配置savePath</br> 输出示例</br> {success:true, msg:'文件上传成功',picId:'1211'}</br>
	 * {success:false, msg:'文件上传失败'}</br>
	 * 
	 * @throws IOException
	 */
	public List<Integer> uploadPic() throws IOException {
		List<Integer> picIds = new ArrayList<Integer>();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
		
			
			savePath = ServletActionContext.getServletContext().getRealPath(
					"/upload");
			File f1 = new File(savePath);
			File f2= new File(savePath+"/mid");
			File f3= new File(savePath+"/small");
			if (!f1.exists()) {
				f1.mkdirs();
			}
			if (!f2.exists()){
				f2.mkdirs();
				
			}
			if (!f3.exists()){
				f3.mkdirs();
			}
			for (int i = 0; i < image.length; i++) {
				
				PicInfo	picInfo=new PicInfo();				
				String urlAppend = System.currentTimeMillis() + "_"
						+ getImageFileName()[i];// +picInfo.getPicName();
				String picPath = savePath + "/" + urlAppend;
				fos = new FileOutputStream(picPath);
				fis = new FileInputStream(getImage()[i]);
				
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				close(fos, fis);
				// 1:上传成功
				picInfo.setPicUrl("/upload/" + urlAppend);
				// ---------------------------1.1 转移到hadoop--------------
				// String hdfsBaseUrl=commonSevice.getParaByCode("hdfsBaseUrl");
				// HdfsUtil.uploadFile(picPath,hdfsBaseUrl+"/"+urlAppend);

				// 2：提交PicInfo
				picInfo.setHilifeId(userInfo.getHilifeId());
				picInfo.setPicName(getImageFileName()[i]);
				int picId = resourceService.savePicInfo(picInfo);
				picIds.add(picId);
				// 3：返回picId
				// pw.write("{success:true, msg:\'文件上传成功\',picId:\'"+picId+"\'}");


				//#############以下处理原图，中图，小图#################
				File freal=new File(picPath);
				File fmid=new File(savePath+"/mid/" + urlAppend);
				File fsmall=new File(savePath+"/small/" + urlAppend);
				
				BufferedImage breal =ImageIO.read(freal); //读取原始图片
				int realWidth= breal.getWidth();// 原图像的长度   
                int realHeight = breal.getHeight();// 原图像的宽度   
                double picscale = (double)realWidth/realHeight;
                int midHeight=700;
                int midWidth=(int)(picscale*midHeight);
                int smallHeight=200;
                int smallWidth=(int)(picscale*smallHeight);
                
                /*
                AffineTransform 类表示 2D 仿射变换，它执行从 2D 坐标到其他 2D
                坐标的线性映射，保留了线的“直线性”和“平行性”。可以使用一系
                列平移、缩放、翻转、旋转和剪切来构造仿射变换。
                */
                AffineTransform transformMid = new AffineTransform();               
                double mx = (double)midWidth/realWidth;
                double my = (double)midHeight/realWidth;
                transformMid.setToScale(mx,my); //setToScale(double sx, double sy) 将此变换设置为缩放变换。
                
                AffineTransform transformSmall = new AffineTransform();               
                double sx = (double)smallWidth/realWidth;
                double sy = (double)smallHeight/realWidth;
                transformSmall.setToScale(sx,sy); //setToScale(double sx, double sy) 将此变换设置为缩放变换。
                //System.out.println(w + " " +h);
                /*
                 * AffineTransformOp类使用仿射转换来执行从源图像或 Raster 中 2D 坐标到目标图像或
                 *  Raster 中 2D 坐标的线性映射。所使用的插值类型由构造方法通过
                 *  一个 RenderingHints 对象或通过此类中定义的整数插值类型之一来指定。
                如果在构造方法中指定了 RenderingHints 对象，则使用插值提示和呈现
                的质量提示为此操作设置插值类型。要求进行颜色转换时，可以使用颜色
                呈现提示和抖动提示。 注意，务必要满足以下约束：源图像与目标图像
                必须不同。 对于 Raster 对象，源图像中的 band 数必须等于目标图像中
                的 band 数。
                */
                AffineTransformOp atoMid = new AffineTransformOp(transformMid,null);
                AffineTransformOp atoSmall = new AffineTransformOp(transformSmall,null);
                BufferedImage bmid = new BufferedImage(midWidth,midHeight,BufferedImage.TYPE_3BYTE_BGR);
                BufferedImage bsmall = new BufferedImage(smallWidth,smallHeight,BufferedImage.TYPE_3BYTE_BGR);
                
                
                
                /*
                 * 测试
                 * TYPE_3BYTE_BGR 表示一个具有 8 位 RGB 颜色分量的图像，
                 * 对应于 Windows 风格的 BGR 颜色模型，具有用 3 字节存
                 * 储的 Blue、Green 和 Red 三种颜色。
                */
                atoMid.filter(breal,bmid);
                atoSmall.filter(breal,bsmall);
                ImageIO.write(bmid,"jpeg",fmid);
                ImageIO.write(bsmall,"jpeg",fsmall); 
			}
			return picIds;
		} catch (Exception e) {
			//pw.write("{success:false, msg:\'文件上传失败\'}");
			e.printStackTrace();
		} finally {
			close(fos, fis);
			return picIds;
		}

	}

	/**
	 * 文件存放目录
	 * 
	 * @return
	 */
	public String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
				fis = null;
			} catch (IOException e) {
				System.out.println("FileInputStream关闭失败");
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
				fis = null;
			} catch (IOException e) {
				System.out.println("FileOutputStream关闭失败");
				e.printStackTrace();
			}
		}
	}


	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
