package org.kyll.myserver.business.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Customer;

/**
 * User: Kyll
 * Date: 2015-06-19 9:30
 */
public interface CustomerService extends BaseService<Customer, Long> {
	Dataset<Customer> get(QueryCondition qc, Paginated pg);

	void save(Customer customer);

	void delete(Long[] ids);
}
