package com.winterframework.logistics.device.dto;

import com.winterframework.logistics.base.enums.Separator;

public class GoogleLocation {
	public Double lat;
	public Double lng;

	public GoogleLocation(){
		
	}

	/**
	 * @param 纬度,经度
	 */
	public GoogleLocation(String location){
		if(null!=location && location.contains(Separator.comma)){
			String[] arr=location.split(Separator.comma);
			lat=Double.valueOf(arr[0]);
			lng=Double.valueOf(arr[1]);
		}
	}


	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		GoogleLocation ll=(GoogleLocation)obj;
		return lat.equals(ll.getLat()) && lng.equals(ll.getLng());
	}
}
