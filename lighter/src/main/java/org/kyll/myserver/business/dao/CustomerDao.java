package org.kyll.myserver.business.dao;

import org.kyll.myserver.base.common.BaseHibernateDao;
import org.kyll.myserver.business.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2015-06-19 9:28
 */
@Repository
public class CustomerDao extends BaseHibernateDao<Customer, Long> {
}
