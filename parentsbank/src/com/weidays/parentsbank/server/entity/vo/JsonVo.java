package com.weidays.parentsbank.server.entity.vo;



/**
 * json输出总 VO
 */
public class JsonVo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4261609318776510032L;
	Object data;
	StateVo state;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public StateVo getState() {
		return state;
	}
	public void setState(StateVo state) {
		this.state = state;
	}
	
	
	
}
