package com.winterframework.logistics.app.service;

public interface IUserAppService {

	Long findCompanyIdByUser(String systemUrl, String queryUser, Long userId);

}
