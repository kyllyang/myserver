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

import java.util.Map;

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
	public Dataset<Map<String, Object>> statArea(QueryCondition qc, Paginated pg) {
		return expenseDao.findBySQL(
				"select t0.area_id_ areaId,\n" +
				"       t1.name_ areaName,\n" +
				"       sum(t0.car_expense_) carExpense,\n" +
				"       sum(t0.city_traffic_expense_) cityTrafficExpense,\n" +
				"       sum(t0.subsidy_expense_) subsidyExpense,\n" +
				"       sum(t0.other_expense_) otherExpense,\n" +
				"       sum(t0.car_expense_) + sum(t0.city_traffic_expense_) + sum(t0.subsidy_expense_) + sum(t0.other_expense_) thisTimeTotal\n" +
				"  from ms_li_expense t0\n" +
				"  join ms_li_area t1\n" +
				"    on t0.area_id_ = t1.id_\n" +
				" group by t0.area_id_, t1.name_\n" +
				" order by t0.area_id_\n", pg);
	}

	@Override
	public Dataset<Map<String, Object>> statCustomer(QueryCondition qc, Paginated pg) {
		return expenseDao.findBySQL(
				"select t0.customer_id_ customerId,\n" +
				"       t1.company_name_ customerCompanyName,\n" +
				"       sum(t0.car_expense_) carExpense,\n" +
				"       sum(t0.city_traffic_expense_) cityTrafficExpense,\n" +
				"       sum(t0.subsidy_expense_) subsidyExpense,\n" +
				"       sum(t0.other_expense_) otherExpense,\n" +
				"       sum(t0.car_expense_) + sum(t0.city_traffic_expense_) + sum(t0.subsidy_expense_) + sum(t0.other_expense_) thisTimeTotal\n" +
				"  from ms_li_expense t0\n" +
				"  join ms_li_customer t1\n" +
				"    on t0.customer_id_ = t1.id_\n" +
				" group by t0.customer_id_, t1.company_name_\n" +
				" order by t0.customer_id_\n", pg);
	}

	@Override
	public Dataset<Map<String, Object>> statProject(QueryCondition qc, Paginated pg) {
		return expenseDao.findBySQL(
				"select t0.project_id_ projectId,\n" +
				"       t1.name_ projectName,\n" +
				"       sum(t0.car_expense_) carExpense,\n" +
				"       sum(t0.city_traffic_expense_) cityTrafficExpense,\n" +
				"       sum(t0.subsidy_expense_) subsidyExpense,\n" +
				"       sum(t0.other_expense_) otherExpense,\n" +
				"       sum(t0.car_expense_) + sum(t0.city_traffic_expense_) + sum(t0.subsidy_expense_) + sum(t0.other_expense_) thisTimeTotal\n" +
				"  from ms_li_expense t0\n" +
				"  join ms_li_project t1\n" +
				"    on t0.project_id_ = t1.id_\n" +
				" group by t0.project_id_, t1.name_\n" +
				" order by t0.project_id_\n", pg);
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
