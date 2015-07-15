package org.kyll.myserver.business.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Project;

/**
 * User: Kyll
 * Date: 2015-07-15 8:46
 */
public interface ProjectService extends BaseService<Project, Long> {
    Dataset<Project> get(QueryCondition qc, Paginated pg);

    void save(Project project);

    void delete(Long[] ids);
}
