package org.kyll.myserver.base.gis.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.dao.OlLayerDao;
import org.kyll.myserver.base.gis.entity.OlLayer;
import org.kyll.myserver.base.gis.service.OlLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-09-09 14:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OlLayerServiceImpl implements OlLayerService {
	@Autowired
	private OlLayerDao olLayerDao;

	@Override
	public OlLayer get(Long id) {
		return olLayerDao.get(id);
	}

	@Override
	public Dataset<OlLayer> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from OlLayer t where 1 = 1");
		String layerClassName = qc.getLayerClassName();
		if (StringUtils.isNotBlank(layerClassName)) {
			hql.append(" and t.layerClassName = '").append(layerClassName).append("'");
		}
		String name = qc.getName();
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and t.name like '%").append(name).append("%'");
		}
		hql.append(" order by t.sort asc");
		return olLayerDao.find(hql, pg);
	}

	@Override
	public void save(OlLayer olLayer) {
		olLayerDao.save(olLayer);
	}

	@Override
	public void delete(Long[] ids) {
		olLayerDao.delete(ids);
	}
}
