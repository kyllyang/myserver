package org.kyll.myserver.business.ctrl;

import org.apache.commons.lang.time.DateUtils;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.NumericalUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Area;
import org.kyll.myserver.business.entity.Customer;
import org.kyll.myserver.business.entity.Expense;
import org.kyll.myserver.business.entity.Project;
import org.kyll.myserver.business.service.AreaService;
import org.kyll.myserver.business.service.CustomerService;
import org.kyll.myserver.business.service.ExpenseService;
import org.kyll.myserver.business.service.ProjectService;
import org.kyll.myserver.business.vo.ExpenseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2015-07-15 14:13
 */
@Controller
@Scope("request")
public class ExpenseCtrl {
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping("/business/expense/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Expense> dataset = expenseService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ExpenseVo> voDataset = POJOUtils.convert(dataset, ExpenseVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/expense/stat/area.ctrl")
	public void statArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Map<String, Object>> dataset = expenseService.statArea(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(dataset));
	}

	@RequestMapping("/business/expense/stat/customer.ctrl")
	public void statCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Map<String, Object>> dataset = expenseService.statCustomer(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(dataset));
	}

	@RequestMapping("/business/expense/stat/project.ctrl")
	public void statProject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Map<String, Object>> dataset = expenseService.statProject(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(dataset));
	}

	@RequestMapping("/business/expense/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Expense entity = expenseService.get(id);
		ExpenseVo entityVo = POJOUtils.convert(entity, ExpenseVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/business/expense/save.ctrl")
	public void save(ExpenseVo entityVo, HttpServletResponse response) throws Exception {
		Expense expense;
		Long id = entityVo.getId();
		if (id == null) {
			expense = new Expense();
		} else {
			expense = expenseService.get(id);
		}
		Long areaId = entityVo.getAreaId();
		if (areaId != null) {
			expense.setArea(areaService.get(areaId));
		}
		Long customerId = entityVo.getCustomerId();
		if (customerId != null) {
			expense.setCustomer(customerService.get(customerId));
		}
		Long projectId = entityVo.getProjectId();
		if (projectId != null) {
			expense.setProject(projectService.get(projectId));
		}
		POJOUtils.copyProperties(expense, entityVo);

		expenseService.save(expense);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/business/expense/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		expenseService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<Expense, ExpenseVo> voHandler = (expense, expenseVo) -> {
		Area area = expense.getArea();
		if (area != null) {
			expenseVo.setAreaId(area.getId());
			expenseVo.setAreaName(area.getName());
		}
		Customer customer = expense.getCustomer();
		if (customer != null) {
			expenseVo.setCustomerId(customer.getId());
			expenseVo.setCustomerCompanyName(customer.getCompanyName());
		}
		Project project = expense.getProject();
		if (project != null) {
			expenseVo.setProjectId(project.getId());
			expenseVo.setProjectName(project.getName());
		}
		Date startDate = expense.getStartDate();
		Date endDate = expense.getEndDate();
		if (startDate != null && endDate != null) {
			expenseVo.setDays(new Long(Math.abs(startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000)).intValue());
		}
		expenseVo.setThisTimeTotal(NumericalUtils.toPrimitive(expense.getCarExpense())
				+ NumericalUtils.toPrimitive(expense.getCityTrafficExpense())
				+ NumericalUtils.toPrimitive(expense.getSubsidyExpense())
				+ NumericalUtils.toPrimitive(expense.getOtherExpense()));
	};
}
