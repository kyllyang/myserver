package org.kyll.myserver.business.service.impl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.ExpenseDao;
import org.kyll.myserver.business.entity.Expense;
import org.kyll.myserver.business.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-07-15 14:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseDao expenseDao;

	@Override
	public Expense get(Long id) {
		return expenseDao.get(id);
	}

	@Override
	public Dataset<Expense> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Expense t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		HqlUtils.appendOrderBy(hql, "t", pg);
		return expenseDao.find(hql, pg);
	}

	@Override
	public void save(Expense expense) {
		expenseDao.save(expense);
	}

	@Override
	public void delete(Long[] ids) {
		expenseDao.delete(ids);
	}

	private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
		if (qc != null) {
		}
		return hql;
	}
}
