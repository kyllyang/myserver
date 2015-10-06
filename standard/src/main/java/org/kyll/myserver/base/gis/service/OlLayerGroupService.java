package org.kyll.myserver.base.gis.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.gis.entity.OlLayerGroup;

/**
 * User: Kyll
 * Date: 2015-10-01 11:59
 */
public interface OlLayerGroupService extends BaseService<OlLayerGroup, Long> {
	JSONArray getTreeJson(Long mapId);

	void save(Long mapId, String layerGroup);
}
