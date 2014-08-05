package com.weidays.parentsbank.server.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.weidays.parentsbank.server.common.BaseAction;
import com.weidays.parentsbank.server.common.util.JSONFormat;
import com.weidays.parentsbank.server.common.util.JsonUtil;
import com.weidays.parentsbank.server.common.util.ServletTool;
import com.weidays.parentsbank.server.entity.po.AreaCode;
import com.weidays.parentsbank.server.entity.vo.DataListVo;
import com.weidays.parentsbank.server.entity.vo.JsonVo;
import com.weidays.parentsbank.server.entity.vo.PageInfoVo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.IAreaCodeService;
/**
 * 区域相关</br>
 * 此类访问目前归类到了用户下：</br>
 * 如：http://localhost:8080/hilifeserver/user/+方法名+.aspx
 * @author Wei
 *
 */
public class AreaCodeAction extends BaseAction
{
    private static final long serialVersionUID = -7253368493741872822L;
    
    private AreaCode areaCode;
    @Autowired
    private IAreaCodeService areaCodeService;
    
    /**
     * 获取已开放区域列表.</br>
     * 无参数</br>
     * @throws IOException
     */
   public void getOpenedAreaList() throws IOException{
   	
       HttpServletResponse response = getResponse();
       getResponse().setCharacterEncoding("UTF-8");
       PrintWriter pw = response.getWriter();
       JsonVo jv=new JsonVo();
       try
       {
      	List areaCodeList= areaCodeService.getOpenedAreaList();
      	StateVo sv;
      	DataListVo dlv;
      	PageInfoVo piv = null;
      	if(areaCodeList.size()>0){
      		sv=new StateVo(STATUS_SUCCESS, "获取成功", "获取成功");
      		piv= new PageInfoVo(0, areaCodeList.size(),10, 10, 1);
      	}else{
      		sv=new StateVo(STATUS_SUCCESS, "暂无开放城市，敬请期待。", "获取成功，但列表数据为0条");
      	}
      	dlv= new DataListVo(areaCodeList, piv, 0);
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
    
	public AreaCode getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(AreaCode areaCode) {
		this.areaCode = areaCode;
	}

    
    
}

