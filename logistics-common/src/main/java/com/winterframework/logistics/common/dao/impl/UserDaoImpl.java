package com.winterframework.logistics.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IUserDao;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.modules.page.Page;
import com.winterframework.modules.web.jsonresult.Pager;
@Repository("userDaoImpl")
public class UserDaoImpl<E extends User>  extends BaseDaoImpl<User> implements IUserDao{
	@Override
	public User getByUserName(String userName) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userName", userName);
		return sqlSessionTemplate.selectOne(getQueryPath("getByUserName"), map);
	}

	@Override
	public int insertUser(User u) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert(getQueryPath("insert"),u);
	}

	@Override
	public int getCountByAttribute(User u) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getCountByAttribute"), u);
	}

	@Override
	public List<User> selectListByAttribute(User u, Pager pager, int count) {
		// TODO Auto-generated method stub
		List<User> users = null;
		if (count == 0 || count < pager.getEndNo()) {
			users = sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), u);
		} else {
			RowBounds rowBounds = dealWithRowBounds(pager, count);
			users = sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), u, rowBounds);
		}
		return users;
	}
	public RowBounds dealWithRowBounds(Pager pager, int count) {
		Page<TransOrder> page = new Page<TransOrder>(pager.getStartNo(), pager.getEndNo(), count);
		return new RowBounds(page.getFirstResult(), page.getPageSize());
	}

}
