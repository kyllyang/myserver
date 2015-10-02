package org.kyll.myserver.base.gis.service.impl;

import org.kyll.myserver.base.gis.dao.OlInteractionDao;
import org.kyll.myserver.base.gis.entity.OlInteraction;
import org.kyll.myserver.base.gis.service.OlInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-02 15:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OlInteractionServiceImpl implements OlInteractionService {
	@Autowired
	private OlInteractionDao olInteractionDao;

	@Override
	public OlInteraction get(Long id) {
		return olInteractionDao.get(id);
	}

	@Override
	public List<OlInteraction> getByOlMap(Long id) {
		return olInteractionDao.find("from OlInteraction t where t.olMap.id = '" + id + "'");
	}
}
