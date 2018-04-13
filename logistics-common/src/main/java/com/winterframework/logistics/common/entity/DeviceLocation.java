package com.winterframework.logistics.common.entity;


import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class DeviceLocation extends ExtBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2121186089806543761L;

	private Long id;

	  private Long deviceId;

	    private Long orderId;

	    private double longitude;

	    private double latitude;

	    private Integer radius;

	    private Long time;

	    private Integer timeStay;

	    private String address;
	    private Integer sourceType;
	    private Long sourceId;
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
	    public enum SourceType{
			//定位源类型（1：GPS 2：BTS 3：WIFI）
	    	GPS(1),BTS(2),WIFI(3); 
			private int _value=1;
			SourceType(int value){
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

	    public double getLongitude() {
	        return longitude;
	    }

	    public void setLongitude(double longitude) {
	        this.longitude = longitude;
	    }

	    public double getLatitude() {
	        return latitude;
	    }

	    public void setLatitude(double latitude) {
	        this.latitude = latitude;
	    }

	    public Integer getRadius() {
	        return radius;
	    }

	    public void setRadius(Integer radius) {
	        this.radius = radius;
	    }

	    public Long getTime() {
	        return time;
	    }

	    public void setTime(Long time) {
	        this.time = time;
	    }

	    public Integer getTimeStay() {
	        return timeStay;
	    }

	    public void setTimeStay(Integer timeStay) {
	        this.timeStay = timeStay;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address == null ? null : address.trim();
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

		/**
		 * @return the orderId
		 */
		public Long getOrderId() {
			return orderId;
		}

		/**
		 * @param orderId the orderId to set
		 */
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public Integer getSourceType() {
			return sourceType;
		}

		public void setSourceType(Integer sourceType) {
			this.sourceType = sourceType;
		}

		public Long getSourceId() {
			return sourceId;
		}

		public void setSourceId(Long sourceId) {
			this.sourceId = sourceId;
		}
		
}