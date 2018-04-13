package com.winterframework.logistics.device.entity;

import com.winterframework.logistics.base.entity.ExtBaseEntity;




public class DeviceCmd extends ExtBaseEntity {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -9171632929509201444L;
	private String imei;
	private String model;
	private String function;
	private String functionDesc;
	private String data;
	private String command;
	private Integer execMode;
	private String execCron;
	private Integer execStatus;
	private Long execTime;
	private Integer execCount;
	private Integer status;
	private Integer save;	//是否需要保存指令
	private Integer reply; //是否需要回复
	
	 public enum ExecMode{
		 	//1:立即执行,2:定时执行; 
	    	IMMEDIATE(1),SCHEDULE(2); 
			private int _value;
			ExecMode(int value){
				this._value=value;
			}
			public int getValue(){
				return this._value;
			}
		}
	 public enum Function{
	    	GB("设置固定上传时间"),BI("设置定时回传"); 
		 private String _value;
		 Function(String value){
			 this._value=value;
		 }
		 public String getDesc(){
			 return _value;
		 }
	}
	 public enum ExecStatus{
		 	/*
		 	0：未执行 初始
		 	1:执行中  下发指令 （2分钟未收到响应 重新执行)
		 	2：成功成功 收到响应
		 	3：执行失败  暂时不存在该状态
		 	4：执行过期 （1天+5分钟）之内未下发成功
		 	5：等待执行 设备不可用或者不在线
		 	*/
	    	INIT(0),EXECUTE(1),SUCCESS(2),FAILED(3),EXPIRED(4),WAITING(5); 
			private int _value;
			ExecStatus(int value){
				this._value=value;
			}
			public int getValue(){
				return this._value;
			}
		}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	
	public String getFunctionDesc() {
		return functionDesc;
	}
	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Integer getExecMode() {
		return execMode;
	}
	public void setExecMode(Integer execMode) {
		this.execMode = execMode;
	}
	public String getExecCron() {
		return execCron;
	}
	public void setExecCron(String execCron) {
		this.execCron = execCron;
	}
	public Integer getExecStatus() {
		return execStatus;
	}
	public void setExecStatus(Integer execStatus) {
		this.execStatus = execStatus;
	}
	public Long getExecTime() {
		return execTime;
	}
	public void setExecTime(Long execTime) {
		this.execTime = execTime;
	}
	
	public Integer getExecCount() {
		return execCount;
	}
	public void setExecCount(Integer execCount) {
		this.execCount = execCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSave() {
		return save;
	}
	public void setSave(Integer save) {
		this.save = save;
	}
	public Integer getReply() {
		return reply;
	}
	public void setReply(Integer reply) {
		this.reply = reply;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append(this.getClass()+":")
				.append(imei).append(" ")
				.append(model).append(" ")
				.append(function).append(" ")
				.append(functionDesc).append(" ")
				.append(data).append(" ")
				.append(command).append(" ")
				.append(execMode).append(" ")
				.append(execCron).append(" ")
				.append(execStatus).append(" ")
				.append(execTime).append(" ")
				.append(status).append(" ")
				.append(save).append(" ")
				.append(reply).append(" ").toString();
	}
}

