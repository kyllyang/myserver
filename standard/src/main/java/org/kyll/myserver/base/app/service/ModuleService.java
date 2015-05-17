package org.kyll.myserver.base.app.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.app.entity.Module;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-11-07 13:07
 */
public interface ModuleService extends BaseService<Module, Long> {
	List<Module> getTopModule(Long userId);

	Dataset<Module> get(QueryCondition qc, Paginated pg);

	JSONArray getTreeJson(Boolean checked, Boolean function, Long roleId);

	void save(Module module, Long parentId);

	void delete(Long[] ids);
}
