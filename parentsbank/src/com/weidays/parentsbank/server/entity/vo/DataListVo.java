package com.weidays.parentsbank.server.entity.vo;


import java.util.List;

/**
 * 用于展示评论的 VO
 */
public class DataListVo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1275406820963697023L;
	List<Object> list;
	PageInfoVo pageInfo;
	int hasMore;
	public DataListVo(List<Object> list,PageInfoVo pageInfo,int hasMore){
		this.list=list;
		this.pageInfo=pageInfo;
		this.hasMore=hasMore;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public PageInfoVo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfoVo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public int getHasMore() {
		return hasMore;
	}
	public void setHasMore(int hasMore) {
		this.hasMore = hasMore;
	}
	
	
}
