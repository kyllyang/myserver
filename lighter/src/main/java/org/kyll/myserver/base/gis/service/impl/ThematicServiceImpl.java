package org.kyll.myserver.base.gis.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.dao.ThematicDao;
import org.kyll.myserver.base.gis.entity.Thematic;
import org.kyll.myserver.base.gis.service.ThematicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-04-28 18:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ThematicServiceImpl implements ThematicService {
	@Autowired
	private ThematicDao thematicDao;

	@Override
	public Thematic get(Long id) {
		return thematicDao.get(id);
	}

	@Override
	public Dataset<Thematic> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Thematic t where 1 = 1");
		String name = qc.getName();
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and t.name like '%").append(name).append("%'");
		}
		hql.append(" order by t.sort asc");
		return thematicDao.find(hql, pg);
	}

	@Override
	public List<Thematic> get(QueryCondition qc) {
		StringBuilder hql;
		Long moduleId = qc.getModuleId();
		if (moduleId == null) {
			hql = new StringBuilder("from Thematic t");
			hql.append(" order by t.sort asc");
		} else {
			hql = new StringBuilder("select t.thematic from MenuApplicationThematic t where t.application.id = '" + moduleId + "'");
			hql.append(" order by t.thematic.sort asc");
		}
		return thematicDao.find(hql);
	}

	@Override
	public void save(Thematic thematic) {
		thematicDao.save(thematic);
	}

	@Override
	public void delete(Long[] ids) {
		thematicDao.delete(ids);
	}
}
