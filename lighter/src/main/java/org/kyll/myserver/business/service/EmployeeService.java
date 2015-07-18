package org.kyll.myserver.business.service;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.entity.Employee;

/**
 * User: Kyll
 * Date: 2014-11-05 13:35
 */
public interface EmployeeService extends BaseService<Employee, Long> {
	Employee login(String username, String password);

	Dataset<Employee> get(QueryCondition qc, Paginated pg);

	boolean save(Employee employee, Long[] areaIds, Long[] roleIds);

	void delete(Long... ids);
}
