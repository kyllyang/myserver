package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.entity.*;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-04-28 18:41
 */
public interface ThematicService extends BaseService<Thematic, Long> {
	Dataset<Thematic> get(QueryCondition qc, Paginated pg);

	List<Thematic> get(QueryCondition qc);

	void save(Thematic thematic, OlMap olMap, OlView olView, String layerGroup, List<OlControl> olControlList, List<OlInteraction> olInteractionList);

	void delete(Long[] ids);
}
