package org.kyll.myserver.base.app.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.app.dao.ModuleDao;
import org.kyll.myserver.base.sys.dao.EmployeeDao;
import org.kyll.myserver.base.sys.dao.RoleDao;
import org.kyll.myserver.base.app.entity.Module;
import org.kyll.myserver.base.sys.entity.Employee;
import org.kyll.myserver.base.sys.entity.Role;
import org.kyll.myserver.base.app.service.ModuleService;
import org.kyll.myserver.base.util.HqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: Kyll
 * Date: 2014-11-07 13:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public Module get(Long id) {
		return moduleDao.get(id);
	}

	@Override
	public List<Module> getApplication(Long employeeId) {
		List<Module> moduleList;
		if (employeeId == null) {
			moduleList = moduleDao.find("from Module t where t.parent is null order by t.sort asc");
		} else {
			Set<Module> moduleSet = new HashSet<>();

			Employee employee = employeeDao.get(employeeId);
			Set<Role> roleSet = employee.getRoleSet();
			for (Role role : roleSet) {
				moduleSet.addAll(role.getFunctionSet().stream().map(this::getTopModule).collect(Collectors.toList()));
			}

			moduleList = new ArrayList<>(moduleSet);
			Collections.sort(moduleList, (o1, o2) -> o1.getSort() - o2.getSort());
		}
		return moduleList;
	}

	private Module getTopModule(Module module) {
		Module parent = module.getParent();
		return parent == null ? module : this.getTopModule(parent);
	}

	@Override
	public Dataset<Module> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Module t where 1 = 1");
		if (qc != null) {
			Long parentId = qc.getParentId();
			if (parentId == null) {
				hql.append(" and t.parent is null");
			} else {
				hql.append(" and t.parent.id = '").append(parentId).append("'");
			}
			String name = qc.getName();
			if (StringUtils.isNotBlank(name)) {
				hql.append(" and lower(t.name) like lower('%").append(name).append("%')");
			}
			String type = qc.getType();
			if (StringUtils.isNotBlank(type)) {
				hql.append(" and t.type = '").append(type).append("'");
			}
			String funcType = qc.getFuncType();
			if (StringUtils.isNotBlank(funcType)) {
				hql.append(" and t.funcType = '").append(funcType).append("'");
			}
		}
		HqlUtils.appendOrderBy(hql, "t", pg);
		return moduleDao.find(hql, pg);
	}

	@Override
	public JSONArray getTreeJson(Boolean checked, Boolean function, Long roleId) {
		List<Module> list = moduleDao.find("from Module t order by t.sort");
		return this.recursiveTree(null, list, checked, function, roleId == null ? new HashSet<>() : roleDao.get(roleId).getFunctionSet());
	}

	private JSONArray recursiveTree(Long parentId, List<Module> list, Boolean checked, Boolean function, Set<Module> modelSet) {
		JSONArray ja = new JSONArray();

		for (Module module : list) {
			Module parent = module.getParent();
			if (parentId == null ? parent == null : parent != null && Objects.equals(parentId, parent.getId())) {
				Long id = module.getId();

				JSONObject jo = new JSONObject();
				jo.put("id", id);
				jo.put("text", module.getName());
				jo.put("type", module.getType());

				if (checked != null) {
					boolean isChecked = checked;
					for (Module m : modelSet) {
						if (Objects.equals(id, m.getId())) {
							isChecked = true;
							break;
						}
					}
					jo.put("checked", isChecked);
				}

				JSONArray children = this.recursiveTree(id, list, checked, function, modelSet);
				if (children.isEmpty()) {
					jo.put("leaf", true);
				} else {
					jo.put("leaf", false);

					if (function != null && function) {
						jo.remove("checked");
					}
				}
				jo.put("children", children);

				ja.add(jo);
			}
		}
		return ja;
	}

	@Override
	public void save(Module module, Long parentId) {
		if (parentId != null) {
			module.setParent(this.get(parentId));
		}
		moduleDao.save(module);
	}

	@Override
	public void delete(Long[] ids) {
		moduleDao.delete(ids);
	}
}
