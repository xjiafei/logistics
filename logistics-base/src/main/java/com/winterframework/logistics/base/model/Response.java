package com.winterframework.logistics.base.model;

import javax.validation.constraints.NotNull;

public class  Response<T>{
	@NotNull
	private int status=0;
	private String message;
	public T data;
	private int count;
	public Response(){}
	public Response(int status,String message){
		this.status=status;
		this.message=message;
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	

}
