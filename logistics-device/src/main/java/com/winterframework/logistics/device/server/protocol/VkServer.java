package com.winterframework.logistics.device.server.protocol;

import java.util.Date;

/**
 * 响应数据
 */
 
public class VkServer{
	private String head; 	//协议头 *VK20 (T128) *MG20 (T19)
    private int save;	//是否需要保存  0x31：1需要；  0x30：0不需要
    private int reply;	//是否需要回复  0x31：1需要；  0x30：0不需要
    private boolean isReply;	//是否是回复  
    private String fnType;	//指令功能类型编码 
    private String fnKey;	//指令功能项关键字
    private String data;	//指令数据
    private String tail;	//协议尾
     
    public VkServer(boolean isT128){
    	if(isT128){
    		this.head="*VK20";
    	}else{
    		this.head="*MG20";
    	}
    	this.save=0;
    	this.reply=0;
    	this.isReply=false;
    	if(isT128){
    		this.tail="&T"+get8BitRandomNumber()+"#";
    	}else{
    		this.tail="#";
    	}
    }
    private String get8BitRandomNumber(){
    	return (new Date().getTime()+"").substring(5);
    }
    public VkServer(VkDevice request){
    	this.head=request.getHead();
    	this.fnType=request.getFnType();
    	this.fnKey=request.getFnKey();
    	this.save=0;
    	this.reply=0;
    	this.isReply=false;
    	this.tail=request.getTail();
    }


	

	public int getSave() {
		return save;
	}

	public void setSave(int save) {
		this.save = save;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}
	
	public boolean isReply() {
		return isReply;
	}

	public void setIsReply(boolean isReply) {
		this.isReply = isReply;
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

	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getTail() {
		return tail;
	}
	public void setTail(String tail) {
		this.tail = tail;
	}
	@Override
    public String toString() { 		    
        return "VkResponse [head=" +head + ", fnType=" + fnType + ", fnKey=" + fnKey+ ", save=" + save 
        		+ ", reply=" + reply + ", isReply=" + isReply+ ", data=" + data+",tail="+tail +"]";
    }
	public String toCommand(){
		if(isReply){
			return head+reply+"Y"+fnType+fnKey+tail;
		}else{
			
		}
		return head+save+reply+fnType+fnKey+data+tail;
	}
}