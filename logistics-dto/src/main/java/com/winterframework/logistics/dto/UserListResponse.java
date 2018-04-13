package com.winterframework.logistics.dto;

import java.util.List;

import com.winterframework.logistics.vo.UserVO;

public class UserListResponse {
	private List<UserVO> userList;

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	
	
	
}
