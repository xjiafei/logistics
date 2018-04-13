package com.winterframework.logistics.device.service;

public interface IDvcDeviceLocationParseService {
	void executeGps();

	void executeBts();

	void executeGoogleReGeocodeByGps();

	void excuteGoogleGeolocationByBts();

}
