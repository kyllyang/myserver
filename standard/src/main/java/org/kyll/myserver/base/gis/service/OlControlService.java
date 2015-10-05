package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.gis.entity.OlControl;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-02 11:29
 */
public interface OlControlService extends BaseService<OlControl, Long> {
	List<OlControl> getByOlMap(Long id, String enabled);
}
