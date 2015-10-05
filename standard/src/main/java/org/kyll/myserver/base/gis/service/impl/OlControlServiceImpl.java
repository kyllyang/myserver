package org.kyll.myserver.base.gis.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.gis.dao.OlControlDao;
import org.kyll.myserver.base.gis.entity.OlControl;
import org.kyll.myserver.base.gis.service.OlControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-02 11:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OlControlServiceImpl implements OlControlService {
	@Autowired
	private OlControlDao olControlDao;

	@Override
	public OlControl get(Long id) {
		return olControlDao.get(id);
	}

	@Override
	public List<OlControl> getByOlMap(Long id, String enabled) {
		StringBuilder hql = new StringBuilder("from OlControl t where t.olMap.id = '" + id + "'");
		if (StringUtils.isNotBlank(enabled)) {
			hql.append(" and t.controlEnabled = '").append(enabled).append("'");
		}
		return olControlDao.find(hql);
	}
}
