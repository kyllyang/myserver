package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.gis.entity.OlView;

/**
 * User: Kyll
 * Date: 2015-10-01 12:25
 */
public interface OlViewService extends BaseService<OlView, Long> {
	OlView getByOlMap(Long id);

	void save(OlView olView, Long mapId);
}
