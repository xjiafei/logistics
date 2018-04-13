package com.winterframework.logistics.base.exception;

public class BizException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4155383900948675082L;
	
	private int code;
	
	public BizException() {
		super();
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}
	public BizException(int code) {
		super();
		this.code = code;
	}
	public BizException(int code,Throwable exception) {
		super(exception);
		this.code = code;
	}
	public BizException(int code, String msg, Throwable exception) {
		super(msg, exception);
		this.code = code;
	}
	public BizException(int code, String msg) {
		this(code, msg,null);
	}
	public int getCode() {
		return this.code;
	}

	@Override
	public String toString() {
		String classname = getClass().getName();
        return classname + ": " + code + " message:"+getMessage();
	}
	public static String getStackTraceMsg(Exception e){
		StringBuffer sb = new StringBuffer();  
        StackTraceElement[] stackArray = e.getStackTrace();  
        for (int i = 0; i < stackArray.length; i++) {  
            StackTraceElement element = stackArray[i];  
            sb.append(element.toString() + "\n");  
        }  
        return sb.toString(); 
	}
	

}
