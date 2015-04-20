package org.kyll.myserver.base.sys.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.QueryCondition;
import org.kyll.myserver.base.sys.entity.DictItem;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2015-02-05 15:34
 */
public interface DictItemService extends BaseService<DictItem, Long> {
	Dataset<DictItem> get(QueryCondition qc, Paginated pg);

	void save(JSONArray jsonArray);

	void delete(Long[] ids);

	Map<String, String> getMap(String invokeCode);
}
