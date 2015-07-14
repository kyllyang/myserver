package org.kyll.myserver.business.service.impl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.CustomerDao;
import org.kyll.myserver.business.dao.CustomerTraceDao;
import org.kyll.myserver.business.entity.Customer;
import org.kyll.myserver.business.entity.CustomerTrace;
import org.kyll.myserver.business.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-06-19 9:32
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerTraceDao customerTraceDao;

	@Override
	public Customer get(Long id) {
		return customerDao.get(id);
	}

	@Override
	public Dataset<Customer> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Customer t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		HqlUtils.appendOrderBy(hql, "t", pg);
		return customerDao.find(hql, pg);
	}

	@Override
	public void save(Customer customer) {
		Long id = customer.getId();
		customerDao.save(customer);
		if (id == null) {
			System.out.println(1 / 0);
			CustomerTrace customerTrace = new CustomerTrace();
			POJOUtils.copyProperties(customerTrace, customer);
			customerTrace.setId(null);
			customerTrace.setCustomer(customer);
			customerTraceDao.save(customerTrace);
		}
	}

	@Override
	public void delete(Long[] ids) {
		customerDao.delete(ids);
	}

	private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
		if (qc != null) {
		}
		return hql;
	}
}
