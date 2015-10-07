package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.gis.entity.OlToolbar;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-07 9:52
 */
public interface OlToolbarService extends BaseService<OlToolbar, Long> {
	List<OlToolbar> getByOlMap(Long id, String enabled);

	void save(Long mapId, List<OlToolbar> olToolbarList);
}
