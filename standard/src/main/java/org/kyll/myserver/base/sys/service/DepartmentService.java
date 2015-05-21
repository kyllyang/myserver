package org.kyll.myserver.base.sys.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.sys.entity.Department;

/**
 * User: Kyll
 * Date: 2015-05-21 17:54
 */
public interface DepartmentService extends BaseService<Department, Long> {
	JSONArray getTreeJson(Boolean checked);

	void save(Department department, Long parentId);

	void delete(Long[] ids);
}
