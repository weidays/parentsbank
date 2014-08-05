package com.weidays.parentsbank.server.entity.vo;



/**
 * 用户返回状态的 VO
 */
public class StateVo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7810091362779186622L;
	int code;//0 成功  其他失败
	String msg;
	String debugMsg;
	
	public  StateVo(int code,String msg,String debugMsg){
		this.code=code;
		this.msg=msg;
		this.debugMsg=debugMsg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDebugMsg() {
		return debugMsg;
	}
	public void setDebugMsg(String debugMsg) {
		this.debugMsg = debugMsg;
	}
	
	
	
}
