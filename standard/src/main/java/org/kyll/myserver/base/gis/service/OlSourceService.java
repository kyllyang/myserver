package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.entity.OlSource;

/**
 * User: Kyll
 * Date: 2015-11-03 21:16
 */
public interface OlSourceService extends BaseService<OlSource, Long> {
	Dataset<OlSource> get(QueryCondition qc, Paginated pg);

	void save(OlSource olSource);

	void delete(Long[] ids);
}
