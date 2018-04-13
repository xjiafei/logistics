package com.winterframework.logistics.base.service; 

import java.util.List;

import com.winterframework.logistics.base.entity.ExtBaseEntity;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;

public interface IBaseService<E extends ExtBaseEntity>{	 
	E get(Long id) throws BizException;
	int save(Context ctx,E entity) throws BizException;
	int save(Context ctx,List<E> entityList) throws BizException;
	int remove(Context ctx,Long id) throws BizException;
	public E selectByAttribute(Context ctx,E entity) throws BizException ;
	public List<E> selectListByAttribute(Context ctx,E entity) throws BizException ;
}
