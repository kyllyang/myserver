package org.kyll.myserver.business.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Expense;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2015-07-15 14:15
 */
public interface ExpenseService extends BaseService<Expense, Long> {
	Dataset<Expense> get(QueryCondition qc, Paginated pg);

	Dataset<Map<String,Object>> statArea(QueryCondition qc, Paginated pg);

	Dataset<Map<String,Object>> statCustomer(QueryCondition qc, Paginated pg);

	Dataset<Map<String,Object>> statProject(QueryCondition qc, Paginated pg);

	void save(Expense expense);

	void delete(Long[] ids);
}
