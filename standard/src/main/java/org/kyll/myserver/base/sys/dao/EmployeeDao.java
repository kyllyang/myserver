package org.kyll.myserver.base.sys.dao;

import org.kyll.myserver.base.common.BaseHibernateDao;
import org.kyll.myserver.base.sys.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2014-11-05 13:33
 */
@Repository
public class EmployeeDao extends BaseHibernateDao<Employee, Long> {
}
