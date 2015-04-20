package org.kyll.myserver.base.sys.dao;

import org.kyll.myserver.base.common.BaseHibernateDao;
import org.kyll.myserver.base.sys.entity.Job;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2014-11-05 13:31
 */
@Repository
public class JobDao extends BaseHibernateDao<Job, Long> {
}
