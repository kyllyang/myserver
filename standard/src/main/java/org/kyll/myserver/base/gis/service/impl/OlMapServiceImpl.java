package org.kyll.myserver.base.gis.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.dao.OlMapDao;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.service.OlMapService;
import org.kyll.myserver.base.util.HqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-08-11 16:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OlMapServiceImpl implements OlMapService {
	@Autowired
	private OlMapDao olMapDao;

	@Override
	public OlMap get(Long id) {
		return olMapDao.get(id);
	}

	@Override
	public Dataset<OlMap> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from OlMap t where 1 = 1");
		String name = qc.getName();
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and lower(t.name) like lower('%").append(name).append("%')");
		}
		HqlUtils.appendOrderBy(hql, "t", pg);
		return olMapDao.find(hql, pg);
	}

	@Override
	public void save(OlMap olMap) {
		olMapDao.save(olMap);
	}

	@Override
	public void delete(Long[] ids) {
		olMapDao.delete(ids);
	}
}
