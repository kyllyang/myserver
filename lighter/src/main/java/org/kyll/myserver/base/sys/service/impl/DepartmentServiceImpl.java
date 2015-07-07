package org.kyll.myserver.base.sys.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kyll.myserver.base.sys.dao.DepartmentDao;
import org.kyll.myserver.base.sys.entity.Department;
import org.kyll.myserver.base.sys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * User: Kyll
 * Date: 2015-05-21 17:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	@Override
	public Department get(Long id) {
		return departmentDao.get(id);
	}

	@Override
	public JSONArray getTreeJson(Boolean checked) {
		List<Department> list = departmentDao.find("from Department t order by t.sort");
		return this.recursiveTree(null, list, checked);
	}

	private JSONArray recursiveTree(Long parentId, List<Department> list, Boolean checked) {
		JSONArray ja = new JSONArray();

		for (Department department : list) {
			Department parent = department.getParent();
			if (parentId == null ? parent == null : parent != null && Objects.equals(parentId, parent.getId())) {
				Long id = department.getId();

				JSONObject jo = new JSONObject();
				jo.put("id", id);
				jo.put("text", department.getName());

				if (checked != null) {
					jo.put("checked", checked);
				}

				JSONArray children = this.recursiveTree(id, list, checked);
				jo.put("leaf", children.isEmpty());
				jo.put("children", children);

				ja.add(jo);
			}
		}
		return ja;
	}

	@Override
	public void save(Department department, Long parentId) {
		if (parentId != null) {
			department.setParent(this.get(parentId));
		}
		departmentDao.save(department);
	}

	@Override
	public void delete(Long[] ids) {
		departmentDao.delete(ids);
	}
}
