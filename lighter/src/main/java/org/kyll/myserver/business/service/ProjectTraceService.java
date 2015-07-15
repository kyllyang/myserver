package org.kyll.myserver.business.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.ProjectTrace;

/**
 * User: Kyll
 * Date: 2015-07-15 10:32
 */
public interface ProjectTraceService extends BaseService<ProjectTrace, Long> {
	Dataset<ProjectTrace> get(QueryCondition qc, Paginated pg);

	void save(ProjectTrace projectTrace);

	void delete(Long[] ids);
}
