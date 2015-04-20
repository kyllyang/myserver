package org.kyll.myserver.base.sys.dao;

import org.kyll.myserver.base.common.BaseHibernateDao;
import org.kyll.myserver.base.sys.entity.User;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2014-11-05 13:06
 */
@Repository
public class UserDao extends BaseHibernateDao<User, Long> {
}
