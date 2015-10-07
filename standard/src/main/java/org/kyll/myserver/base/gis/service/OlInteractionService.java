package org.kyll.myserver.base.gis.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.gis.entity.OlInteraction;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-02 15:17
 */
public interface OlInteractionService extends BaseService<OlInteraction, Long> {
	List<OlInteraction> getByOlMap(Long id, String enabled);

	void save(Long mapId, List<OlInteraction> olInteractionList);
}
