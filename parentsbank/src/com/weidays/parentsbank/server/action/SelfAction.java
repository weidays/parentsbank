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
import com.weidays.parentsbank.server.service.ISharePicService;
/**
 * 通过用户信息获取的一半内容。如：最近动态，与我相关等，</br>
* 此类的访问：归类到了self下：</br>
* 如：http://localhost:8080/hilifeserver/self/+方法名+.aspx
* @author Wei
*
*/
public class SelfAction extends BaseAction
{
    private static final long serialVersionUID = -7253368493741872822L;
    
    private BankOwner userInfo;
    //当前页面
    private int page=1;
   
    @Autowired
    private ISharePicService sharePicService;
   
    /**
     * 用户动态</br>
     * 参数要求：</br>
     *  UserInfo.hilifeId=?</br>
     *  page=？ </br>
     * 输出示例：</br>
     *  
     * @see UserInfo</br>
     * @throws IOException 
     */
    public void newsFeed() throws IOException{
    	 HttpServletRequest request = getRequest();
         HttpServletResponse response = getResponse();
         getResponse().setCharacterEncoding("UTF-8");
         PrintWriter pw = response.getWriter();
         JsonVo jv=new JsonVo();
         //获取动态分页大小
         String newsFeedPageSize=commonSevice.getParaByCode("newsFeedPageSize");
         int start=(page-1)*Integer.parseInt(newsFeedPageSize);
         int pageSize=Integer.parseInt(newsFeedPageSize);
         try
         {
        	int total=0;
        	total= sharePicService.getNewsFeedCount(userInfo.getId());
        	List list= sharePicService.getNewsFeed(userInfo.getId(),start,pageSize);
        	StateVo sv;
          	DataListVo dlv;
          	PageInfoVo piv = null;
          	int hasMore=0;
          	if(list.size()>0){
          		sv=new StateVo(STATUS_SUCCESS, "获取成功", "获取成功");
          		piv= new PageInfoVo(total, list.size(),pageSize, total%pageSize==0?(total/pageSize):(total/pageSize+1),page);
          	}else{
          		sv=new StateVo(STATUS_SUCCESS, "没有更多", "没有更多");
          	}
          	dlv= new DataListVo(list, piv, (total-(pageSize*page)<=0?0:1));
          	jv.setData(dlv);
          	jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
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
     * 与我相关</br>
     * 参数要求：</br>
     *  UserInfo.hilifeId=?</br>
     *  page=？ </br>
     * 输出示例：</br>
     *  
     * @see UserInfo</br>
     * @throws IOException 
     */
    public void atMe() throws IOException{
    	 HttpServletRequest request = getRequest();
         HttpServletResponse response = getResponse();
         getResponse().setCharacterEncoding("UTF-8");
         PrintWriter pw = response.getWriter();
         JsonVo jv=new JsonVo();
         //获取动态分页大小
         String newsFeedPageSize=commonSevice.getParaByCode("newsFeedPageSize");
         int start=(page-1)*Integer.parseInt(newsFeedPageSize);
         int pageSize=Integer.parseInt(newsFeedPageSize);
         try
         {
        	int total=0;
        	total= sharePicService.getAtMeCount(userInfo.getId());
        	List list= sharePicService.getAtMe(userInfo.getId(),start,pageSize);
        	StateVo sv;
          	DataListVo dlv;
          	PageInfoVo piv = null;
          	int hasMore=0;
          	if(list.size()>0){
          		sv=new StateVo(STATUS_SUCCESS, "获取成功", "获取成功");
          		piv= new PageInfoVo(total, list.size(),pageSize, total%pageSize==0?(total/pageSize):(total/pageSize+1),page);
          	}else{
          		sv=new StateVo(STATUS_SUCCESS, "没有更多", "没有更多");
          	}
          	dlv= new DataListVo(list, piv, (total-(pageSize*page)<=0?0:1));
          	jv.setData(dlv);
          	jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
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

