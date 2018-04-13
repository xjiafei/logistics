package com.winterframework.logistics.portal.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.portal.dao.IPtlQuotationDao;
import com.winterframework.logistics.portal.entity.Quotation;
@Repository("ptlQuotationDaoImpl")
public class PtlQuotationDaoImpl <E extends Quotation> extends BaseDaoImpl<Quotation> implements IPtlQuotationDao{

}
