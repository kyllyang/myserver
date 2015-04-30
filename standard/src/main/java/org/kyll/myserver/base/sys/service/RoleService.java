package org.kyll.myserver.base.sys.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.entity.Role;

/**
 * User: Kyll
 * Date: 2014-11-10 14:35
 */
public interface RoleService extends BaseService<Role, Long> {
	JSONArray getTreeJson(Long userId);

	Dataset<Role> get(QueryCondition qc, Paginated pg);

	void save(JSONArray jsonArray);

	void save(Long roleId, Long[] moduleIds);

	void delete(Long[] ids);
}
