package com.winterframework.logistics.base.dao;

import java.util.List;

import com.winterframework.logistics.base.entity.ExtBaseEntity;
import com.winterframework.orm.dal.ibatis3.BaseDao;


public interface IBaseDao<E extends ExtBaseEntity> extends BaseDao<E>{
	int insertBatch(List<E> entitys) throws Exception;
	int updateBatch(List<E> entitys) throws Exception;
	E selectByAttribute(E entity) throws Exception;
	List<E> selectListByAttribute(E entity) throws Exception;
}