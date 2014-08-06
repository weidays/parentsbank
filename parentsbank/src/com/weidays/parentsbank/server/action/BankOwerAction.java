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
import com.weidays.parentsbank.server.entity.po.BankOwner;
import com.weidays.parentsbank.server.entity.vo.JsonVo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.IBankOwnerService;
/**
 * 用户相关，</br>
* 此类的访问：归类到了user下：</br>
* 如：http://localhost:8080/hilifeserver/user/+方法名+.aspx
* @author Wei
*
*/
public class BankOwerAction extends BaseAction
{
    private static final long serialVersionUID = -7253368493741872822L;
    
    private BankOwner bankOwner;
   /**
    * 更新操作是,用于指定更新哪个字段,内容与bankOwner的属性名保持一致
    */
    private String updateContent;
    @Autowired
    private IBankOwnerService bankOwnerService;
    /**
     * 用户注册</br>
     * 参数要求：</br>
     *  BankOwner的所有字段</br>
     *  如bankOwner.userName=？ </br>
     * 输出示例：</br>
     *  {success:true, msg:'注册成功'}</br>
     * @see BankOwner</br>
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
        	 List userList=bankOwnerService.saveOrUpdateBankOwner(bankOwner);
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
     *  bankOwner.hilifeId=？ </br>
     * 输出示例：</br>
     *   </br>
     * @see BankOwner
     * @throws IOException 
     */
    public void getUserDetail() throws IOException{
    	
         HttpServletResponse response = getResponse();
         getResponse().setCharacterEncoding("UTF-8");
         PrintWriter pw = response.getWriter();
         JsonVo jv=new JsonVo();
         try
         {
        	List userList= bankOwnerService.getUserDetailByHilifeId(bankOwner.getId());
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
     *  bankOwner.userName=？ </br>
     *  bankOwner.password=？ </br>
     * @throws IOException
     */
	public void login() throws IOException {

		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		 JsonVo jv=new JsonVo();
		try {
			BankOwner ui = bankOwnerService.userLogin(bankOwner);
			StateVo sv=new StateVo(STATUS_FAILED, "用户名或密码错误", "");
			if (ui != null) {
				setSession("bankOwner", ui);
				setSession("hilifeId", ui.getId());
				
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
     *   1.bankOwner.hilifeId=？</br>
     *   2.updateContent=?  这个问号（？）就是你要更新的字段。字段名称就是BankOwner中的字段。</br>
     *   3.bankOwner.x=?   要更新哪个字段就需要为该字段赋值，x表示要更新的字段也就是同上个参数的？</br>
     * 输出示例：</br>
     *    </br>
     * @see BankOwner
     * @throws IOException 
     */
    public void updateBankOwner() throws IOException{
    	
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
        	boolean flag= bankOwnerService.updateBankOwner(bankOwner,updateContent);
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
	public BankOwner getBankOwner() {
		return bankOwner;
	}

	public void setBankOwner(BankOwner bankOwner) {
		this.bankOwner = bankOwner;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	

    
    
}

