package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.entity.OlMap;

/**
 * User: Kyll
 * Date: 2015-08-11 16:40
 */
public interface OlMapService extends BaseService<OlMap, Long> {
	Dataset<OlMap> get(QueryCondition qc, Paginated pg);

	void save(OlMap olMap);

	void delete(Long[] ids);
}
