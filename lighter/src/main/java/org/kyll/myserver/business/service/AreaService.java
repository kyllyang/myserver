package org.kyll.myserver.business.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Area;

/**
 * User: Kyll
 * Date: 2015-07-15 13:39
 */
public interface AreaService extends BaseService<Area, Long> {
	Dataset<Area> get(QueryCondition qc, Paginated pg);

	void save(JSONArray jsonArray);

	void delete(Long[] ids);
}
