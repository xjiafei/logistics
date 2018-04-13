package com.winterframework.logistics.device.server.protocol;

/**
 * 请求数据
 */
 
public class VkDevice{
	private String head; 	//协议头 *VK20
    /*private byte start;	//起始符 *
    private byte vk1;	// VK标识V
    private byte vk2;	// VK标识K
    private byte version;	//协议版本号：20
*/    
	private int reply;	//是否需要回复  0x31：1需要；  0x30：0不需要
    private boolean isReply;	//是否是回复
    private String terminalId;	//终端ID 手机号码
    private String fnType;	//指令功能类型编码 
    private String fnKey;	//指令功能项关键字
    private String data;	//指令数据
    private String tail;	//协议尾
    private String ip;	//请求IP
 
    public VkDevice(){
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


	public String getTerminalId() {
		return terminalId;
	}


	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
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

	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
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
        return "VkRequest [head="+head+", terminalId=" + terminalId  + ", fnType=" + fnType + ", fnKey=" + fnKey
        		+ ", reply=" + reply+ ", isReply=" + isReply+ ", data=" + data+ ", ip=" + ip +",tail="+tail +"]";
    }
	public String toCommand(){
		if(isReply){
			return head+"Y"+fnType+fnKey+tail;
		}else{
			
		}
		return head+reply+terminalId+fnType+fnKey+data+tail;
	}
}