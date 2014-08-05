package com.weidays.parentsbank.server.entity.vo;



/**
 * 用于页面信息的 VO
 */
public class PageInfoVo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8634050432347828789L;
	int total;
	int count;
	int pageSize;
	int pageCount;
	int page;
	/**
	 * 
	 * @param total
	 * @param count
	 * @param pageSize
	 * @param pageCount
	 * @param page
	 */
	public  PageInfoVo(int total,int count,int pageSize,int pageCount,int page){
		this.total=total;
		this.count=count;
		this.pageSize=pageSize;
		this.pageCount=pageCount;
		this.page=page;
	}
	

	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
	
}
