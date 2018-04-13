package com.winterframework.logistics.common.entity;


import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class DeviceLocationSemi extends ExtBaseEntity {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 3402386051821334670L;

	  	private String imei;
	    private double longitude;
	    private double latitude;
	    private Integer radius;
	    private Integer distance;
	    private String address;
	    private Long timeBegin;
	    private Long timeEnd;
	    private Integer sourceType;
	    private Long sourceId;
	    private Integer status;
	    private Integer handleStatus;


	    public enum HandleStatus{
			//0未处理 1已完成  4已废弃 
	    	INIT(0),FINISHED(1),DELETED(4); 
			private int _value=1;
			HandleStatus(int value){
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
		public String getImei() {
			return imei;
		}
		public void setImei(String imei) {
			this.imei = imei;
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
		public Integer getDistance() {
			return distance;
		}
		public void setDistance(Integer distance) {
			this.distance = distance;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Long getTimeBegin() {
			return timeBegin;
		}
		public void setTimeBegin(Long timeBegin) {
			this.timeBegin = timeBegin;
		}
		public Long getTimeEnd() {
			return timeEnd;
		}
		public void setTimeEnd(Long timeEnd) {
			this.timeEnd = timeEnd;
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
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Integer getHandleStatus() {
			return handleStatus;
		}
		public void setHandleStatus(Integer handleStatus) {
			this.handleStatus = handleStatus;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof DeviceLocationSemi){
				DeviceLocationSemi no=(DeviceLocationSemi)obj;
				return no.getId().longValue()==this.getId().longValue();
			}
			return false;
		}
	    
	    
}