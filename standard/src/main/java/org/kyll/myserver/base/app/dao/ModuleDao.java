package org.kyll.myserver.base.app.dao;

import org.kyll.myserver.base.common.BaseHibernateDao;
import org.kyll.myserver.base.app.entity.Module;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2014-11-05 13:30
 */
@Repository
public class ModuleDao extends BaseHibernateDao<Module, Long> {
}
