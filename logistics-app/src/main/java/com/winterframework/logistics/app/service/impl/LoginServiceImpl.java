package com.winterframework.logistics.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.app.exception.AppException;
import com.winterframework.logistics.app.service.ILoginService;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.http.HttpUtil;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.dto.LoginRequest;
import com.winterframework.logistics.dto.LoginResponse;
import com.winterframework.modules.spring.exetend.PropertyConfig;

/**
 * 用户登录服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("loginServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements ILoginService {
	@PropertyConfig("server.url.system")
	private String serverSystemUrl;
	@PropertyConfig("system.action.path.login")
	private String actionLoginPath;
	@PropertyConfig("system.action.path.logout")
	private String actionLogoutPath;
	@PropertyConfig("logistics.system.company.findByAttribute")
	private String findCompanyPath;
	@Override
	public LoginResponse login(String userName, String passwd, String loginIp) throws AppException {
		LoginRequest LoginRequest=new LoginRequest();
		LoginRequest.setUserName(userName);
		LoginRequest.setPasswd(passwd);
		LoginRequest.setLoginIp(loginIp);
		
		try{
			Response<LoginResponse> res=HttpUtil.http(serverSystemUrl,actionLoginPath,LoginRequest,LoginResponse.class);
	        if(null!=res && res.getStatus()==0){
	        	return res.getData();
	        }else{
	        	throw new AppException(res.getStatus());
	        }
        }catch(BizException e){
        	throw new AppException(e.getCode(),e.getMessage(),e);
        }
	}
	@Override
	public void logout(Long userId) throws AppException {
		try{
			Response<String> res=HttpUtil.http(serverSystemUrl,actionLogoutPath,userId,String.class);
	        if(null!=res && res.getStatus()!=0){
	        	throw new AppException(res.getStatus());
	        }
        }catch(BizException e){
        	throw new AppException(e.getCode(),e.getMessage(),e);
        }
	}
	@Override
	public Company getCompanyName(Long companyId) throws AppException  {
		// TODO Auto-generated method stub
		Company company=new Company();
		company.setId(companyId);
		try {
			Response<Company> res=HttpUtil.http(serverSystemUrl, findCompanyPath,company ,Company.class);
			if(res !=null && res.getStatus() ==0) {
				return res.getData();
			}else {
				throw new AppException(res.getStatus(),res.getMessage(),null);
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			throw new AppException(e.getCode(),e.getMessage(),e);
		}
	}
	
}
