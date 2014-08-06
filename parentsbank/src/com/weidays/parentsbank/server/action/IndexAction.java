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
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.opensymphony.xwork2.ActionContext;
import com.weidays.parentsbank.server.common.BaseAction;
import com.weidays.parentsbank.server.common.util.JacksonMapper;
import com.weidays.parentsbank.server.common.util.JsonUtil;
import com.weidays.parentsbank.server.common.util.ServletTool;
import com.weidays.parentsbank.server.entity.po.BankOwner;
import com.weidays.parentsbank.server.entity.vo.DataListVo;
import com.weidays.parentsbank.server.entity.vo.JsonVo;
import com.weidays.parentsbank.server.entity.vo.PageInfoVo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.ICommonService;
import com.weidays.parentsbank.server.service.IIndexService;
import com.weidays.parentsbank.server.service.ISharePicService;
import com.weidays.parentsbank.server.service.IBankOwnerService;
import com.weidays.parentsbank.server.service.impl.BankOwnerService;
/**
 * 通过用户信息获取的一半内容。如：最近动态，与我相关等，</br>
* 此类的访问：归类到了index下：</br>
* 如：http://localhost:8080/hilifeserver/index/+方法名+.aspx
* @author Wei
*
*/
public class IndexAction extends BaseAction
{
    private static final long serialVersionUID = -7253368493741872822L;
    
    private BankOwner userInfo;
    
    //当前页面
    private int page=1;
   
    @Autowired
    private IIndexService indexService;
    @Autowired
    private IBankOwnerService userInfoService;
   
   


	
    
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
	public BankOwner getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(BankOwner userInfo) {
		this.userInfo = userInfo;
	}




	public int getPage() {
		return page;
	}




	public void setPage(int page) {
		this.page = page;
	}



	

    
    
}


