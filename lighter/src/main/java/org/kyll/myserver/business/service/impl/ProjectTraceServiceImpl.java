package org.kyll.myserver.business.service.impl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.ProjectTraceDao;
import org.kyll.myserver.business.entity.ProjectTrace;
import org.kyll.myserver.business.service.ProjectTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-07-15 10:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectTraceServiceImpl implements ProjectTraceService {
	@Autowired
	private ProjectTraceDao projectTraceDao;

	@Override
	public ProjectTrace get(Long id) {
		return projectTraceDao.get(id);
	}

	@Override
	public Dataset<ProjectTrace> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from ProjectTrace t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		HqlUtils.appendOrderBy(hql, "t", pg);
		return projectTraceDao.find(hql, pg);
	}

	@Override
	public void save(ProjectTrace projectTrace) {
		projectTraceDao.save(projectTrace);
	}

	@Override
	public void delete(Long[] ids) {
		projectTraceDao.delete(ids);
	}

	private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
		if (qc != null) {
			Long projectId = qc.getProjectId();
			if (projectId != null) {
				hql.append(" and t.project.id = '").append(projectId).append("'");
			}
		}
		return hql;
	}
}
