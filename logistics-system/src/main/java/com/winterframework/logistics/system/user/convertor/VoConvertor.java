package com.winterframework.logistics.system.user.convertor;

import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.vo.UserVO;

public class VoConvertor {

	public static UserVO toUserVo(User user){
		UserVO vo=new UserVO(); 
		vo.setId(user.getId());
		vo.setUserName(user.getUserName());
		vo.setNickName(user.getNickName());
		vo.setHeadImg(user.getHeadImg());
		vo.setPasswd(user.getPasswd());
		vo.setPhoneNumber(user.getPhoneNumber());
		vo.setType(user.getType());
		vo.setRemark(user.getRemark());
		vo.setCreatorId(user.getCreatorId());
		vo.setCreateTime(user.getCreateTime());
		vo.setUpdatorId(user.getUpdatorId());
		vo.setUpdateTime(user.getUpdateTime());
		
		return vo;
	}
	
}
