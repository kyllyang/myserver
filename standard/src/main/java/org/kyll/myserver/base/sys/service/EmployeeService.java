package org.kyll.myserver.base.sys.service;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.entity.Employee;

/**
 * User: Kyll
 * Date: 2014-11-05 13:35
 */
public interface EmployeeService extends BaseService<Employee, Long> {
	Employee login(String username, String password);

	Dataset<Employee> get(QueryCondition qc, Paginated pg);

	boolean save(Employee employee, Long departmentId);

	void save(Long employeeId, Long[] roleIds);

	void delete(Long... ids);
}
