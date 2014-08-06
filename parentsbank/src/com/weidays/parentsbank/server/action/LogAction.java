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
import com.weidays.parentsbank.server.entity.po.CommentLog;
import com.weidays.parentsbank.server.entity.po.FavourLog;
import com.weidays.parentsbank.server.entity.po.BankOwner;
import com.weidays.parentsbank.server.entity.vo.DataListVo;
import com.weidays.parentsbank.server.entity.vo.JsonVo;
import com.weidays.parentsbank.server.entity.vo.PageInfoVo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.ILogService;
import com.weidays.parentsbank.server.service.impl.CommonService;
/**
 * 日志记录相关，目前包括评论记录和赞记录</br>
 * 此类的访问：归类到了log下：</br>
 * 如：http://localhost:8080/hilifeserver/log/+方法名+.aspx
 * @author Wei
 *
 */
public class LogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3069690703028744027L;
	private CommentLog commentLog;
	private BankOwner userInfo;
	private FavourLog favourLog;
	private int page=1;
	@Autowired
	private ILogService logService;
	/**
	 * 根据业务类型和业务ID获取Comment</br>
	 * 参数要求</br>
	 * 	1.commentLog.busiId=?</br>
	 *  2.commentLog.busiType=?</br>
	 * 输出示例：</br>
	 * 	  待定</br>
	 * @throws IOException
	 */

	public void getComment() throws IOException {

		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		JsonVo jv=new JsonVo();
		PrintWriter pw = response.getWriter();
		//获取动态分页大小
        String newsFeedPageSize=commonSevice.getParaByCode("newsFeedPageSize");
        int start=(page-1)*Integer.parseInt(newsFeedPageSize);
        int pageSize=Integer.parseInt(newsFeedPageSize);
		try {
			//TODO 还需要确认返回格式
			StateVo sv;
           	DataListVo dlv;
           	PageInfoVo piv = null;
           	int total=0;
           	total= logService.getCommentsCount(commentLog.getBusiId(),commentLog.getBusiType());
           	if(total==0){
           	sv=new StateVo(STATUS_SUCCESS, "暂无评论", "暂无评论");
           	jv.setState(sv);
     		JsonUtil.printJsonVo(jv, response);
     		return;
           	}
			List comentList = logService.getComment(commentLog.getBusiId(),commentLog.getBusiType(),start,pageSize);
			if(comentList.size()>0){
          		sv=new StateVo(STATUS_SUCCESS, "获取成功", "获取成功");
          		piv= new PageInfoVo(total, comentList.size(),pageSize, total%pageSize==0?(total/pageSize):(total/pageSize+1),page);
          	}else{
          		sv=new StateVo(STATUS_SUCCESS, "没有更多", "没有更多");
          	}
			
			dlv= new DataListVo(comentList, piv, (total-(pageSize*page)<=0?0:1));
          	jv.setData(dlv);
          	jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
		} catch (Exception e) {
			e.printStackTrace();
			 StateVo sv=new StateVo(STATUS_FAILED, "获取失败", this.formatMsg(e.getMessage()));
            jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
		}
	}
	/**
	 * 评论
	 *  参数要求
	 *  除commentLog.hilifeId的其他全部字段
	 *  @see CommentLog 
	 *  　如：
	 * 	1.commentLog.busiId=?
	 *  2.commentLog.busiType=?
	 *  输出示例：
	 *   {success:true, msg:'评论成功'}
	 * @throws IOException
	 */
	public void commentIt() throws IOException {
		
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		JsonVo jv=new JsonVo();
		try {
			commentLog.setHilifeId(userInfo.getId());
			StateVo sv;
			int commentCount = logService.commentIt(commentLog);
		 	sv=new StateVo(STATUS_SUCCESS, "评论成功", "评论成功");
		 	jv.setData(commentCount);
          	jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv=new StateVo(STATUS_FAILED, "评论失败", this.formatMsg(e.getMessage()));
	        jv.setState(sv);
	        JsonUtil.printJsonVo(jv, response);
		}
	}
	
	/**
	 * 统计评论数 </br>
	 * 参数要求 </br> 
	 * 		1.commentLog.busiId=?  </br>
	 * 		2.commentLog.busiType=?  </br>
	 * 输出示例： </br>
	 *     {success:true, msg:'获取成功',commentCount:'10'}
	 * @throws IOException　 </br>
	 * @see CommentLog 
	 */
	public void getCommentCount() throws IOException {
		
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		JsonVo jv=new JsonVo();
		try {
			StateVo sv;
			int commentCount = logService.getCommentCount(commentLog.getBusiId(),commentLog.getBusiType());
		 	sv=new StateVo(STATUS_SUCCESS, "获取成功", "获取成功");
		 	jv.setData(commentCount);
          	jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv=new StateVo(STATUS_FAILED, "获取失败", this.formatMsg(e.getMessage()));
            jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
		}
	}
	/**
	 *统计赞数
	 * 参数要求 </br> 
	 * 		1.favourLog.busiId=?  </br>
	 * 		2.favourLog.busiType=?  </br>
	 * 输出示例： </br>
	 *     {success:true, msg:'获取成功',favourLog:'10'}
	 * @throws IOException
	 * @see FavourLog
	 */
	public void getFavourCount() throws IOException {
		
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		JsonVo jv=new JsonVo();
		try {
			StateVo sv;
			int favourCount = logService.getFavourCount(favourLog.getBusiId(),favourLog.getBusiType());
			sv=new StateVo(STATUS_SUCCESS, "获取成功", "获取成功");
		 	jv.setData(favourCount);
          	jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
			//pw.write("{success:true, msg:\'获取成功\',favourCount:\'"+favourCount+"\'}");
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv=new StateVo(STATUS_FAILED, "获取失败", this.formatMsg(e.getMessage()));
            jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
		}
	}
	/**
	 * 点赞
	 * 参数要求 :</br> 
	 * FavourLog 中除了hilifeId 其他所有字段
	 * 如：
	 * 		1.favourLog.busiId=?  </br>
	 * 		2.favourLog.busiType=?  </br>
	 * 输出示例： </br>
	 * 	成功：   {success:true, msg:'赞成功'}
	 *  失败：   {success:false, msg:'具体异常信息'}
	 * @throws IOException
	 */
	public void favourIt() throws IOException {
		
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		JsonVo jv=new JsonVo();
		try {
			favourLog.setHilifeId(userInfo.getId());
			StateVo sv;
			int favourCount = logService.favourIt(favourLog);
			sv=new StateVo(STATUS_SUCCESS, "赞成功", "返回最新赞统计数");
		 	jv.setData(favourCount);
          	jv.setState(sv);
    		JsonUtil.printJsonVo(jv, response);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			StateVo sv=new StateVo(STATUS_FAILED, "赞失败", this.formatMsg(e.getMessage()));
            jv.setState(sv);
		}
	}


	public CommentLog getCommentLog() {
		return commentLog;
	}

	public void setCommentLog(CommentLog commentLog) {
		this.commentLog = commentLog;
	}
	public FavourLog getFavourLog() {
		return favourLog;
	}
	public void setFavourLog(FavourLog favourLog) {
		this.favourLog = favourLog;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public BankOwner getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(BankOwner userInfo) {
		this.userInfo = userInfo;
	}


}
