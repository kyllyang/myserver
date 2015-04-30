package org.kyll.myserver.base.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.dao.EmployeeDao;
import org.kyll.myserver.base.sys.dao.RoleDao;
import org.kyll.myserver.base.sys.entity.Employee;
import org.kyll.myserver.base.sys.entity.Role;
import org.kyll.myserver.base.sys.service.EmployeeService;
import org.kyll.myserver.base.util.HqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2014-11-05 13:35
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public Employee login(String username, String password) {
		List<Employee> employeeList = employeeDao.find("from Employee t where t.username = '" + username + "' and t.password = '" + password + "' and t.freeze <> '1'");
		return employeeList.isEmpty() ? null : employeeList.get(0);
	}

	@Override
	public Employee get(Long id) {
		return employeeDao.get(id);
	}

	@Override
	public Dataset<Employee> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Employee t where 1 = 1");
		String username = qc.getUsername();
		if (StringUtils.isNotBlank(username)) {
			hql.append(" and lower(t.username) like lower('%").append(username).append("%')");
		}
		HqlUtils.appendOrderBy(hql, "t", pg);
		return employeeDao.find(hql, pg);
	}

	@Override
	public boolean save(Employee user) {
		List<Employee> userList = employeeDao.find("from Employee t where t.username = '" + user.getUsername() + "'");
		boolean result = userList.isEmpty();
		if (!result) {
			result = Objects.equals(userList.get(0).getId(), user.getId());
		}
		if (result) {
			employeeDao.save(user);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(Long userId, Long[] roleIds) {
		Query query = roleDao.createQuery("from Role t where t.id in (:roleIds)");
		query.setParameterList("roleIds", roleIds);
		List<Role> roleList = query.list();

		Employee user = employeeDao.get(userId);
		Set<Role> roleSet = user.getRoleSet();
		roleSet.clear();
		roleSet.addAll(roleList);
		employeeDao.save(user);
	}

	@Override
	public void delete(Long... ids) {
		employeeDao.delete(ids);
	}
}
