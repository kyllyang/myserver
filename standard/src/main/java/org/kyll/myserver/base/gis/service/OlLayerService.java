package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.entity.OlLayer;

/**
 * User: Kyll
 * Date: 2015-09-09 14:45
 */
public interface OlLayerService extends BaseService<OlLayer, Long> {
	Dataset<OlLayer> get(QueryCondition qc, Paginated pg);

	void save(OlLayer olLayer);

	void delete(Long[] ids);
}
