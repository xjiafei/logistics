package com.winterframework.logistics.common.entity;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class DeviceBattery extends ExtBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 630496021868639234L;

	private Long id;

	   private Long deviceId;

	    private Long orderId;

	    private Integer battery;

	    private Long time;

	    private Integer status;

	    private String remark;

	    private Long creatorId;

	    private Long updatorId;
	   
	    public enum Status{
			//0.不可用 1.可用 
	    	NO(0),YES(1); 
			private int _value=1;
			Status(int value){
				this._value=value;
			}
			public int getValue(){
				return this._value;
			}
		}
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public Long getDeviceId() {
	        return deviceId;
	    }

	    public void setDeviceId(Long deviceId) {
	        this.deviceId = deviceId;
	    }

	    public Integer getBattery() {
	        return battery;
	    }

	    public void setBattery(Integer battery) {
	        this.battery = battery;
	    }

	    public Long getTime() {
	        return time;
	    }

	    public void setTime(Long time) {
	        this.time = time;
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }

	    public Long getCreatorId() {
	        return creatorId;
	    }

	    public void setCreatorId(Long creatorId) {
	        this.creatorId = creatorId;
	    }

	    public Long getUpdatorId() {
	        return updatorId;
	    }

	    public void setUpdatorId(Long updatorId) {
	        this.updatorId = updatorId;
	    }

	    public Long getOrderId() {
			return orderId;
		}

	    public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

}