package com.winterframework.logistics.base.service;
 
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.base.entity.ExtBaseEntity;
import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;

public abstract class BaseServiceImpl<DAO extends IBaseDao<E>, E extends ExtBaseEntity> implements IBaseService<E> { 
	protected Logger log = LoggerFactory.getLogger(getClass()); 
	
	protected abstract DAO getEntityDao();
	
  	public E get(Long id) throws BizException{
		return (E)getEntityDao().getById(id);
	}

	public int save(Context ctx,E entity) throws BizException {
		if(null!=entity){
			try{
				if(null==entity.getId()){
					entity.setCreatorId(ctx.getUserId());
					entity.setGmtCreated(DateUtils.currentDate());
					entity.setCreateTime(DateUtils.getCurTime());
					return getEntityDao().insert(entity); 
				}else{
					entity.setUpdatorId(ctx.getUserId());
					entity.setUpdateTime(DateUtils.getCurTime());
					entity.setGmtModified(DateUtils.currentDate());
					return getEntityDao().update(entity);
				}
			}catch(Exception e){
				log.error("save failed.",e);
				throw new BizException(StatusCode.DAO_ERROR.getCode());
			}
		}
		return 0;
	}
	@Override
	@Transactional
	public int save(Context ctx,List<E> entityList) throws BizException {
		if(null!=entityList){
			int count=0;
			try{
				List<E> insertList=new ArrayList<E>();
				List<E> updateList=new ArrayList<E>();
				for(E entity:entityList){
					if(null==entity.getId()){
						entity.setCreatorId(ctx.getUserId());
						entity.setGmtCreated(DateUtils.currentDate());
						entity.setCreateTime(System.currentTimeMillis());
						insertList.add(entity);
					}else{
						entity.setUpdatorId(ctx.getUserId());
						entity.setUpdateTime(System.currentTimeMillis());
						entity.setGmtModified(DateUtils.currentDate());
						updateList.add(entity);
					}
				}
				count= getEntityDao().insertBatch(insertList)+getEntityDao().updateBatch(updateList);
			}catch(Exception e){
				log.error("save failed.",e);
				throw new BizException(StatusCode.DAO_ERROR.getCode(),StatusCode.DAO_ERROR.getMessage(),e);
			}
			return count;
		}
		return 0;
	}

	public int remove(Context ctx,Long id) throws BizException {
		if(null!=id){
			try{
				return getEntityDao().delete(id);
			}catch(Exception e){
				log.error("remove failed.",e);
				throw new BizException(StatusCode.DAO_ERROR.getCode(),StatusCode.DAO_ERROR.getMessage(),e);
			}
		}
		return 0;
	}
	
	public E selectByAttribute(Context ctx,E entity) throws BizException {
		try{
			return  getEntityDao().selectByAttribute(entity);
		}catch(Exception e){
			log.error("remove failed.",e);
			throw new BizException(StatusCode.DAO_ERROR.getCode(),StatusCode.DAO_ERROR.getMessage(),e);
		}
	}

	public List<E> selectListByAttribute(Context ctx,E entity) throws BizException {
		if (entity == null) {
			return new ArrayList<E>();
		}
		try{
			return getEntityDao().selectListByAttribute( entity);
		}catch(Exception e){
			log.error("remove failed.",e);
			throw new BizException(StatusCode.DAO_ERROR.getCode(),StatusCode.DAO_ERROR.getMessage(),e);
		}
			
	}
	
}
 