package org.kyll.myserver.business.dao;

import org.kyll.myserver.base.common.BaseHibernateDao;
import org.kyll.myserver.business.entity.Expense;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2015-07-15 13:37
 */
@Repository
public class ExpenseDao extends BaseHibernateDao<Expense, Long> {
}
