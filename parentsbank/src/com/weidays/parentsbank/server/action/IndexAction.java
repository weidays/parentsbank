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
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.entity.vo.DataListVo;
import com.weidays.parentsbank.server.entity.vo.JsonVo;
import com.weidays.parentsbank.server.entity.vo.PageInfoVo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.ICommonService;
import com.weidays.parentsbank.server.service.IIndexService;
import com.weidays.parentsbank.server.service.ISharePicService;
import com.weidays.parentsbank.server.service.IUserInfoService;
import com.weidays.parentsbank.server.service.impl.UserInfoService;
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
    
    private UserInfo userInfo;
    
    //当前页面
    private int page=1;
   
    @Autowired
    private IIndexService indexService;
    @Autowired
    private IUserInfoService userInfoService;
   
    /**
     * 获取首页列表</br>
     * 参数要求：</br>
     
     *  page=？ </br>
     * 输出示例：</br>
     *  
     * @see UserInfo</br>
     * @throws IOException 
     */
    public void newsFeed() throws IOException{
         HttpServletResponse response = getResponse();
         getResponse().setCharacterEncoding("UTF-8");
         JsonVo jv=new JsonVo();
         //获取动态分页大小
         String newsFeedPageSize=commonSevice.getParaByCode("newsFeedPageSize");
         int start=(page-1)*Integer.parseInt(newsFeedPageSize);
         int pageSize=Integer.parseInt(newsFeedPageSize);
         try
         {
        	StateVo sv;
           	DataListVo dlv;
           	PageInfoVo piv = null;
           	int hasMore=0;
        	 
        	//如果没有穿 userInfo.areaCode 参数，就通过 userInfo.hilifeId 去获取。
        	if(userInfo!=null){
        		if(userInfo.getHilifeId()!=null&&userInfo.getAreaCode()==null){
        			UserInfo temp=(UserInfo) userInfoService.getUserDetailByHilifeId(userInfo.getHilifeId()).get(0);
        			userInfo.setAreaCode(temp.getAreaCode());
        		}
        	}else{
        		//返回说明参数有问题
        		sv=new StateVo(STATUS_FAILED, "获取失败", "参数传入不正确，hilifeId 和areaCode必须有一个");
                jv.setState(sv);
        		JsonUtil.printJsonVo(jv, response);
        		return;
        	}
        	 
        	int total=0;
        	total= indexService.getNewsFeedCount(userInfo);
        	List list= indexService.getNewsFeed(userInfo,start,pageSize);
        	
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
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}




	public int getPage() {
		return page;
	}




	public void setPage(int page) {
		this.page = page;
	}



	

    
    
}


