package com.weidays.parentsbank.server.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.weidays.parentsbank.server.common.BaseAction;
import com.weidays.parentsbank.server.common.util.JsonUtil;
import com.weidays.parentsbank.server.common.util.ServletTool;
import com.weidays.parentsbank.server.entity.bo.PictureShareBo;
import com.weidays.parentsbank.server.entity.po.UserInfo;
import com.weidays.parentsbank.server.entity.vo.JsonVo;
import com.weidays.parentsbank.server.entity.vo.StateVo;
import com.weidays.parentsbank.server.service.ISharePicService;

/**
 * 图片分享相关，</br> 使用PictureShareBo 这个bo 对象来获取参数。</br> 其中picIds用来存放 图片ID 多个图片ID
 * 用'$'符号分开！</br> 此类的访问：归类到了pic下：</br>
 * 如：http://localhost:8080/hilifeserver/pic/+方法名+.aspx
 * 
 * @author Wei
 * 
 */
public class SharePicAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3069690703028744027L;
	private PictureShareBo picShare;
	@Autowired
	private ISharePicService sharePicService;

	/**
	 * 图片分享</br> 参数要求</br> PictureShareBo 中除picShare.hilifeId的其他全部字段</br>
	 * 　如：</br> 1.picShare.title=?</br> 2.picShare.content=?</br>
	 * 3.picShare.hilifeId=?</br> 输出示例：</br> {success:true, msg:'分享成功'}</br>
	 * 
	 * @throws IOException
	 * @see PictureShareBo
	 */
	public void sharePic() throws IOException {
		List<Integer> picIds = uploadPic();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		JsonVo jv = new JsonVo();
		try {
			picShare.setHilifeId(getUserInfo().getHilifeId());
			picShare.setPicIds(picIds);
			if (sharePicService.sharePic(picShare)) {
				StateVo sv = new StateVo(STATUS_SUCCESS, "分享成功", "分享成功");
				jv.setState(sv);
				JsonUtil.printJsonVo(jv, response);
			} else {
				StateVo sv = new StateVo(STATUS_SUCCESS, "分享失败", "分享失败");
				jv.setState(sv);
				JsonUtil.printJsonVo(jv, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv = new StateVo(STATUS_FAILED, "分享失败", this.formatMsg(e.getMessage()));
			jv.setState(sv);
			JsonUtil.printJsonVo(jv, response);
			
		}
	}

	/**
	 * 转载分享</br> 参数要求：</br> 1.picShare.source=02</br>
	 * 2.picShare.fromShareId=?</br> 输出示例：</br> {success:true, msg:'转载成功'}</br>
	 * 
	 * @throws IOException
	 */
	public void reshipPic() throws IOException {

		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		JsonVo jv = new JsonVo();
		try {
//			UserInfo ui = getUserSession();
//			if (ui == null) {
//				StateVo sv = new StateVo(1, "会话过期", "会话过期");
//				jv.setState(sv);
//				JsonUtil.printJsonVo(jv, response);
//				return;
//			}
			picShare.setHilifeId(getUserInfo().getHilifeId());
			if (sharePicService.reshipPic(picShare)) {
				StateVo sv = new StateVo(STATUS_SUCCESS, "转载成功", "转载成功");
				jv.setState(sv);
				JsonUtil.printJsonVo(jv, response);
			} else {
				StateVo sv = new StateVo(STATUS_FAILED, "转载失败", "转载失败");
				jv.setState(sv);
				JsonUtil.printJsonVo(jv, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv = new StateVo(STATUS_FAILED, "转载失败", this.formatMsg(e.getMessage()));
			jv.setState(sv);
			JsonUtil.printJsonVo(jv, response);
		}
	}

	/**
	 * 分享删除 参数要求：</br> picShare.shareId=?</br> 输出示例：</br> {success:true,
	 * msg:'删除成功'}</br>
	 * 
	 * @throws IOException
	 */
	public void shareDel() throws IOException {

		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		JsonVo jv = new JsonVo();
		try {
//			UserInfo ui = getUserSession();
//			if (ui == null) {
//
//				StateVo sv = new StateVo(1, "会话过期", "会话过期");
//				jv.setState(sv);
//				JsonUtil.printJsonVo(jv, response);
//				return;
//			}
			picShare.setHilifeId(getUserInfo().getHilifeId());
			if (sharePicService.shareDel(picShare)) {
				StateVo sv = new StateVo(STATUS_SUCCESS, "删除成功", "删除成功");
				jv.setState(sv);
				JsonUtil.printJsonVo(jv, response);
			} else {
				StateVo sv = new StateVo(STATUS_FAILED, "删除失败", "删除失败");
				jv.setState(sv);
				JsonUtil.printJsonVo(jv, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv = new StateVo(STATUS_FAILED, "删除失败", this.formatMsg(e.getMessage()));
			jv.setState(sv);
			JsonUtil.printJsonVo(jv, response);
		}
	}

	/**
	 * 获取分享明细 参数要求：</br> picShare.shareId=?</br> 输出示例：</br>
	 * 
	 * @throws IOException
	 */
	public void shareDetail() throws IOException {
		HttpServletResponse response = getResponse();
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		JsonVo jv = new JsonVo();
		try {
			Object obj = sharePicService.getShareDetail(picShare.getShareId());
			if(obj==null){
				StateVo sv = new StateVo(STATUS_FAILED, "获取失败", "查无数据，检查参数是否正确");
				jv.setState(sv);
				JsonUtil.printJsonVo(jv, response);
				return;
			}
			StateVo sv = new StateVo(STATUS_SUCCESS, "", "成功获取");
			jv.setData(obj);
			jv.setState(sv);
			JsonUtil.printJsonVo(jv, response);
		} catch (Exception e) {
			e.printStackTrace();
			StateVo sv = new StateVo(STATUS_FAILED, "获取失败", this.formatMsg(e.getMessage()));
			jv.setState(sv);
			JsonUtil.printJsonVo(jv, response);
		}
	}

	public PictureShareBo getPicShare() {
		return picShare;
	}

	public void setPicShare(PictureShareBo picShare) {
		this.picShare = picShare;
	}

}
