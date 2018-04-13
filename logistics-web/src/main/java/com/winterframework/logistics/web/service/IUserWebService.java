package com.winterframework.logistics.web.service;

import java.util.List;

import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IUserWebService {

	Response<List<User>> getUserList(String systemUrl, String queryListUrl, String account, Pager pager);

	Long findCompanyIdByUser(String systemUrl, String queryUser, Long userId);

}
