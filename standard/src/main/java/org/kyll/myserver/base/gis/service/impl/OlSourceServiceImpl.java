package org.kyll.myserver.base.gis.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.dao.OlSourceDao;
import org.kyll.myserver.base.gis.entity.OlSource;
import org.kyll.myserver.base.gis.service.OlSourceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Kyll
 * Date: 2015-11-03 21:17
 */
public class OlSourceServiceImpl implements OlSourceService {
	@Autowired
	private OlSourceDao olSourceDao;

	@Override
	public OlSource get(Long id) {
		return olSourceDao.get(id);
	}

	@Override
	public Dataset<OlSource> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from OlSource t where 1 = 1");
		String sourceType = qc.getSourceType();
		if (StringUtils.isNotBlank(sourceType)) {
			hql.append(" and t.sourceType = '").append(sourceType).append("'");
		}
		String sourceClassName = qc.getSourceClassName();
		if (StringUtils.isNotBlank(sourceClassName)) {
			hql.append(" and t.sourceClassName = '").append(sourceClassName).append("'");
		}
		String name = qc.getName();
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and t.name like '%").append(name).append("%'");
		}
		hql.append(" order by t.sort asc");
		return olSourceDao.find(hql, pg);
	}

	@Override
	public void save(OlSource olSource) {
		olSourceDao.save(olSource);
	}

	@Override
	public void delete(Long[] ids) {
		olSourceDao.delete(ids);
	}
}
