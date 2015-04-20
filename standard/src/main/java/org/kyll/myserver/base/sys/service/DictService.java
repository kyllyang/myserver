package org.kyll.myserver.base.sys.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.sys.entity.Dict;

/**
 * User: Kyll
 * Date: 2014-11-08 15:59
 */
public interface DictService extends BaseService<Dict, Long> {
	JSONArray getTreeJson(Long parentId);

	void save(Dict dict, Long parentId);

	void delete(Long[] ids);
}
