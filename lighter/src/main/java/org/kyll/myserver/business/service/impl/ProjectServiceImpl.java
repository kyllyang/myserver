package org.kyll.myserver.business.service.impl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.ProjectDao;
import org.kyll.myserver.business.entity.Project;
import org.kyll.myserver.business.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-07-15 8:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;

    @Override
    public Project get(Long id) {
        return projectDao.get(id);
    }

    @Override
    public Dataset<Project> get(QueryCondition qc, Paginated pg) {
        StringBuilder hql = new StringBuilder("from Project t where 1 = 1");
        this.appendQueryCondition(hql, qc);
        HqlUtils.appendOrderBy(hql, "t", pg);
        return projectDao.find(hql, pg);
    }

    @Override
    public void save(Project project) {
        projectDao.save(project);
    }

    @Override
    public void delete(Long[] ids) {
        projectDao.delete(ids);
    }

    private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
        if (qc != null) {
            Long employeeId = qc.getEmployeeId();
            if (employeeId != null) {
                hql.append(" and t.customer.employee.id = '").append(employeeId).append("'");
            }
        }
        return hql;
    }
}
