package com.winterframework.logistics.device.server.notification;

public class Notification {
	private String target;	//推送目标 
	private String fnType;	//功能类型
	private String fnKey;	//功能指令
	private String data;	//指令内容
	private boolean isSave;	//是否需要保存指令
	private boolean isReply; //是否需要回复
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getFnType() {
		return fnType;
	}
	public void setFnType(String fnType) {
		this.fnType = fnType;
	}
	public String getFnKey() {
		return fnKey;
	}
	public void setFnKey(String fnKey) {
		this.fnKey = fnKey;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean getIsSave() {
		return isSave;
	}
	public void setIsSave(boolean isSave) {
		this.isSave = isSave;
	}
	public boolean getIsReply() {
		return isReply;
	}
	public void setIsReply(boolean isReply) {
		this.isReply = isReply;
	}
	@Override
	public String toString() {
		return new StringBuffer().append(getClass()+":")
				.append(target).append(" ")
				.append(fnType).append(" ")
				.append(fnKey).append(" ")
				.append(isSave).append(" ")
				.append(isReply).append(" ").toString();
	}
	
	
	
}
