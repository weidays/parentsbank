package com.weidays.parentsbank.server.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.weidays.parentsbank.server.common.BaseAction;
import com.weidays.parentsbank.server.common.util.JacksonMapper;
import com.weidays.parentsbank.server.common.util.JsonUtil;
import com.weidays.parentsbank.server.common.util.ServletTool;
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.entity.vo.JsonVo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.IUserInfoService;
/**
 * 用户相关，</br>
* 此类的访问：归类到了user下：</br>
* 如：http://localhost:8080/hilifeserver/user/+方法名+.aspx
* @author Wei
*
*/
public class UserInfoAction extends BaseAction
{
    private static final long serialVersionUID = -7253368493741872822L;
    
    private UserInfo userInfo;
   /**
    * 更新操作是,用于指定更新哪个字段,内容与userInfo的属性名保持一致
    */
    private String updateContent;
    @Autowired
    private IUserInfoService userInfoService;
    /**
     * 用户注册</br>
     * 参数要求：</br>
     *  UserInfo的所有字段</br>
     *  如userInfo.userName=？ </br>
     * 输出示例：</br>
     *  {success:true, msg:'注册成功'}</br>
     * @see UserInfo</br>
     * @throws IOException 
     */
    public void userRegist() throws IOException{
    	
    	 HttpServletRequest request = getRequest();
         HttpServletResponse response = getResponse();
         getResponse().setCharacterEncoding("UTF-8");
         PrintWriter pw = response.getWriter();
         JsonVo jv=new JsonVo();
         try
         {
        	 List userList=userInfoService.saveOrUpdateUserInfo(userInfo);
        	if(userList.size()>0){
        		StateVo sv=new StateVo(STATUS_SUCCESS, "", "注册成功");
        		jv.setData(userList.get(0));
        		jv.setState(sv);
        		JsonUtil.printJsonVo(jv, response);
        	}else{
	        	StateVo sv=new StateVo(STATUS_SUCCESS, "注册成功", "注册成功");
	    		jv.setState(sv);
	    		JsonUtil.printJsonVo(jv, response);
        	}
         }
         catch (Exception e)
         {
             e.printStackTrace();
             StateVo sv=new StateVo(STATUS_FAILED, "注册失败", this.formatMsg(e.getMessage()));
         	jv.setState(sv);
         	JsonUtil.printJsonVo(jv, response);
         }
    }
    /**
     * 用户资料</br>
     * 参数要求：</br>
     *  userInfo.hilifeId=？ </br>
     * 输出示例：</br>
     *   </br>
     * @see UserInfo
     * @throws IOException 
     */
    public void getUserDetail() throws IOException{
    	
         HttpServletResponse response = getResponse();
         getResponse().setCharacterEncoding("UTF-8");
         PrintWriter pw = response.getWriter();
         JsonVo jv=new JsonVo();
         try
         {
        	List userList= userInfoService.getUserDetailByHilifeId(userInfo.getHilifeId());
        	if(userList.size()>0){
        		StateVo sv=new StateVo(STATUS_SUCCESS, "", "成功获取");
        		jv.setData(userList.get(0));
        		jv.setState(sv);
        		JsonUtil.printJsonVo(jv, response);
        	}else{
	        	StateVo sv=new StateVo(STATUS_FAILED, "获取失败", "获取失败");
	    		jv.setState(sv);
	    		JsonUtil.printJsonVo(jv, response);
        	}
         }
         catch (Exception e)
         {
             e.printStackTrace();
             StateVo sv=new StateVo(STATUS_FAILED, "获取失败", this.formatMsg(e.getMessage()));
	    		jv.setState(sv);
	    		JsonUtil.printJsonVo(jv, response);
         }
    }
    
    /**
     * 用户登录</br>
     * 参数要求：</br>
     *  userInfo.userName=？ </br>
     *  userInfo.password=？ </br>
     * @throws IOException
     */
	public void login() throws IOException {

		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		 JsonVo jv=new JsonVo();
		try {
			UserInfo ui = userInfoService.userLogin(userInfo);
			StateVo sv=new StateVo(STATUS_FAILED, "用户名或密码错误", "");
			if (ui != null) {
				setSession("userInfo", ui);
				setSession("hilifeId", ui.getHilifeId());
				
				sv=new StateVo(STATUS_SUCCESS, "登陆成功", "");
			} 
			jv.setState(sv);
			jv.setData(ui);
			JsonUtil.printJsonVo(jv, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv=new StateVo(STATUS_FAILED, "登陆失败", this.formatMsg(e.getMessage()));
			jv.setState(sv);
			JsonUtil.printJsonVo(jv, response);
		}
		
	}


	/**
     * 用户资料</br>
     * 参数要求：</br>
     *   1.userInfo.hilifeId=？</br>
     *   2.updateContent=?  这个问号（？）就是你要更新的字段。字段名称就是UserInfo中的字段。</br>
     *   3.userInfo.x=?   要更新哪个字段就需要为该字段赋值，x表示要更新的字段也就是同上个参数的？</br>
     * 输出示例：</br>
     *    </br>
     * @see UserInfo
     * @throws IOException 
     */
    public void updateUserInfo() throws IOException{
    	
         HttpServletResponse response = getResponse();
         getResponse().setCharacterEncoding("UTF-8");
         PrintWriter pw = response.getWriter();
         JsonVo jv=new JsonVo();
//         if(getUserSession()==null){
//     		StateVo sv=new StateVo(1, "会话过期", "会话过期");
//			jv.setState(sv);
//			JsonUtil.printJsonVo(jv, response);
//     		return;
//     	}
         try
         {
        	boolean flag= userInfoService.updateUserInfo(userInfo,updateContent);
        	if(flag){
        		StateVo sv=new StateVo(STATUS_SUCCESS, "更新成功", "更新成功");
    			jv.setState(sv);
    			JsonUtil.printJsonVo(jv, response);
        	}else{
        		StateVo sv=new StateVo(STATUS_FAILED, "更新失败", "更新失败");
    			jv.setState(sv);
    			JsonUtil.printJsonVo(jv, response);
        	}
         }
         catch (Exception e)
         {
             e.printStackTrace();
             StateVo sv=new StateVo(STATUS_FAILED, "更新失败", this.formatMsg(e.getMessage()));
 			jv.setState(sv);
 			JsonUtil.printJsonVo(jv, response);
         }
    }
    
    /**
     * 设置session
     * @param name
     * @param value
     */
	public static void setSession(Object name, Object value) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		session.put(name, value);
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	

    
    
}

