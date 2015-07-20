package org.kyll.myserver.business.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.dao.DepartmentDao;
import org.kyll.myserver.business.dao.EmployeeDao;
import org.kyll.myserver.business.dao.RoleDao;
import org.kyll.myserver.business.entity.Area;
import org.kyll.myserver.business.entity.Employee;
import org.kyll.myserver.business.entity.Role;
import org.kyll.myserver.business.service.EmployeeService;
import org.kyll.myserver.base.util.HqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
		List<Employee> employeeList = employeeDao.find("from Employee t where t.username = '" + username + "' and t.password = '" + org.kyll.myserver.base.util.StringUtils.encryptSHA(password) + "' and t.freeze <> '1'");
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
		Long departmentId = qc.getDepartmentId();
		if (departmentId != null) {
			hql.append(" and t.department.id = '").append(departmentId).append("'");
		}
		HqlUtils.appendOrderBy(hql, "t", pg);
		return employeeDao.find(hql, pg);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean save(Employee employee, Long[] areaIds, Long[] roleIds) {
		List<Employee> userList = employeeDao.find("from Employee t where t.username = '" + employee.getUsername() + "'");
		boolean result = userList.isEmpty();
		if (!result) {
			result = Objects.equals(userList.get(0).getId(), employee.getId());
		}
		if (result) {
			employeeDao.save(employee);

			Set<Area> areaSet = employee.getAreaSet();
			if (areaSet == null) {
				areaSet = new HashSet<>();
				employee.setAreaSet(areaSet);
			}
			areaSet.clear();
			if (areaIds != null) {
				Query query = roleDao.createQuery("from Area t where t.id in (:areaIds)");
				query.setParameterList("areaIds", areaIds);
				areaSet.addAll(query.list());
			}

			Set<Role> roleSet = employee.getRoleSet();
			if (roleSet == null) {
				roleSet = new HashSet<>();
				employee.setRoleSet(roleSet);
			}
			roleSet.clear();
			if (roleIds != null) {
				Query query = roleDao.createQuery("from Role t where t.id in (:roleIds)");
				query.setParameterList("roleIds", roleIds);
				roleSet.addAll(query.list());
			}

			employeeDao.save(employee);
		}
		return result;
	}

	@Override
	public void delete(Long... ids) {
		employeeDao.delete(ids);
	}

	@Override
	public JSONArray getTreeJson() {
		JSONArray ja = new JSONArray();
		List<Employee> employeeList = employeeDao.find("from Employee t order by t.sort asc");
		for (Employee employee : employeeList) {
			JSONObject jo = new JSONObject();
			jo.put("id", org.kyll.myserver.base.util.StringUtils.generateUUID() + "_employee_" + employee.getId());
			jo.put("text", employee.getName());
		//	jo.put("expanded", true);

			Set<Area> areaSet = employee.getAreaSet();
			if (areaSet.isEmpty()) {
				jo.put("leaf", true);
			} else {
				jo.put("leaf", false);

				JSONArray children = new JSONArray();
				for (Area area : areaSet) {
					JSONObject child = new JSONObject();
					child.put("id", org.kyll.myserver.base.util.StringUtils.generateUUID() + "_area_" + area.getId());
					child.put("text", area.getName());
				//	child.put("expanded", true);
					child.put("leaf", true);
					children.add(child);
				}
				jo.put("children", children);
			}

			ja.add(jo);
		}
		return ja;
	}
}
