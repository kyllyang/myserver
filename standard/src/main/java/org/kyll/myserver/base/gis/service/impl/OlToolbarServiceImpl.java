package org.kyll.myserver.base.gis.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.gis.dao.OlMapDao;
import org.kyll.myserver.base.gis.dao.OlToolbarDao;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.entity.OlToolbar;
import org.kyll.myserver.base.gis.service.OlToolbarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-07 9:58
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OlToolbarServiceImpl implements OlToolbarService {
	@Autowired
	private OlToolbarDao olToolbarDao;
	@Autowired
	private OlMapDao olMapDao;

	@Override
	public OlToolbar get(Long id) {
		return olToolbarDao.get(id);
	}

	@Override
	public List<OlToolbar> getByOlMap(Long id, String enabled) {
		StringBuilder hql = new StringBuilder("from OlToolbar t where t.olMap.id = '" + id + "'");
		if (StringUtils.isNotBlank(enabled)) {
			hql.append(" and t.toolbarEnabled = '").append(enabled).append("'");
		}
		return olToolbarDao.find(hql);
	}

	@Override
	public void save(Long mapId, List<OlToolbar> olToolbarList) {
		OlMap olMap = olMapDao.get(mapId);

		olToolbarDao.delete(olToolbarDao.find("from OlToolbar t where t.olMap.id = '" + olMap.getId() + "'"));
		for (OlToolbar olControl : olToolbarList) {
			olControl.setOlMap(olMap);
			olToolbarDao.save(olControl);
		}
	}
}
