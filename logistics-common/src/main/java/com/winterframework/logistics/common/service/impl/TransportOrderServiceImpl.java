package com.winterframework.logistics.common.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.ITransOrderDao;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.SearchAttribute;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.common.service.ITransportOrderService;
import com.winterframework.modules.web.jsonresult.Pager;
@Service("transOrderServiceImpl")
public class TransportOrderServiceImpl   extends BaseServiceImpl<ITransOrderDao,TransOrder> implements ITransportOrderService{
	
	@Resource(name="transOrderDaoImpl")
	ITransOrderDao transportOrderDao;
	@Resource(name="deviceServiceImpl")
	private IDeviceService deviceService;

	@Override
	public Long addTransportOrder(TransOrder transOrderList) {
		// TODO Auto-generated method stub
		try {
			Device device=deviceService.getByNumber(transOrderList.getDeviceNumber());
			if(device==null){
				throw new LmException(StatusCode.IMEI_INVALID);
			}
			device.setLocationFreq(transOrderList.convertFreq2Mins(transOrderList.getLocationFreqType(),transOrderList.getLocationFreq()));
			deviceService.save(new Context(transOrderList.getCreatorId()), device);
			return transportOrderDao.insertOrder(transOrderList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}

	@Override
	public List<TransOrder> findAllTransportOrder(TransOrder transOrder) {
		// TODO Auto-generated method stub
		try {
			return transportOrderDao.selectListByAttribute(transOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ITransOrderDao getEntityDao() {
		// TODO Auto-generated method stub
		return transportOrderDao;
	}

	@Override
	public int updateTransportStatus(TransOrder transOrder) {
		try {
			return transportOrderDao.update(transOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public TransOrder findByAttribute(TransOrder transOrder) {
		// TODO Auto-generated method stub
		try {
			return transportOrderDao.selectByAttribute(transOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transOrder;
		
	}

	@Override
	public int deleteTransport(TransOrder transOrder) {
		// TODO Auto-generated method stub
		return transportOrderDao.deleteOrder(transOrder);
	}

	@Override
	public List<TransOrder> searchByCode(String code) {
		// TODO Auto-generated method stub
		return transportOrderDao.searchTransportOrderByCode(code);
	}

	@Override
	public List<TransOrder> searchByAttribute(SearchAttribute searchAttribute,Pager pager,int count) {
		// TODO Auto-generated method stub
		return transportOrderDao.searchTransportOrderByAttribute(searchAttribute,pager,count);
	}

	@Override
	public List<TransOrder> findListTransportOrderByPager(TransOrder transOrder, Pager pager,int count) {
		// TODO Auto-generated method stub
		return transportOrderDao.searchTransportOrderByAttributeAndPager(transOrder,pager,count);
	}

	@Override
	public int getCount(TransOrder transOrder) {
		// TODO Auto-generated method stub
		return transportOrderDao.getCount(transOrder);
	}

	@Override
	public int searchListCount(SearchAttribute searchAttribute) {
		// TODO Auto-generated method stub
		return transportOrderDao.searchListCount(searchAttribute);
	}

	@Override
	public List<TransOrder> searchTransportOrderByNumber(String code) {
		// TODO Auto-generated method stub
		return transportOrderDao.searchTransportOrderByNumber(code);
	}

	@Override
	public int searchTransportCountByNumber(String code) {
		// TODO Auto-generated method stub
		return transportOrderDao.searchTransportCountByNumber(code);
	}

	@Override
	public List<TransOrder> getHistoryByAttribute(TransOrder transOrder, Pager pager, int count) {
		// TODO Auto-generated method stub
		return transportOrderDao.searchTransportOrderHistoryByAttributeAndPager(transOrder,pager,count);
	}


}
