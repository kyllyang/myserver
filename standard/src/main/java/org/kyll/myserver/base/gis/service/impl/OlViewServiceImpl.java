package org.kyll.myserver.base.gis.service.impl;

import org.kyll.myserver.base.gis.dao.OlViewDao;
import org.kyll.myserver.base.gis.entity.OlView;
import org.kyll.myserver.base.gis.service.OlViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-01 12:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OlViewServiceImpl implements OlViewService {
	@Autowired
	private OlViewDao olViewDao;

	@Override
	public OlView get(Long id) {
		return olViewDao.get(id);
	}

	@Override
	public OlView getByOlMap(Long id) {
		List<OlView> list = olViewDao.find("from OlView t where t.olMap.id = '" + id + "'");
		return list.isEmpty() ? null : list.get(0);
	}
}
