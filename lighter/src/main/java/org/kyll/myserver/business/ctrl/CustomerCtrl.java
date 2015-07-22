package org.kyll.myserver.business.ctrl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.base.util.StringUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Customer;
import org.kyll.myserver.business.service.CustomerService;
import org.kyll.myserver.business.service.EmployeeService;
import org.kyll.myserver.business.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-06-19 9:35
 */
@Controller
@Scope("request")
public class CustomerCtrl {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/business/customer/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Customer> dataset = customerService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<CustomerVo> voDataset = POJOUtils.convert(dataset, CustomerVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/customer/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Customer entity = customerService.get(id);
		CustomerVo entityVo = POJOUtils.convert(entity, CustomerVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/business/customer/save.ctrl")
	public void save(CustomerVo entityVo, HttpServletResponse response) throws Exception {
		Customer customer;
		Long id = entityVo.getId();
		if (id == null) {
			customer = new Customer();
			Long employeeId = entityVo.getEmployeeId();
			if (employeeId != null) {
				customer.setEmployee(employeeService.get(employeeId));
			}
		} else {
			customer = customerService.get(id);
		}
		POJOUtils.copyProperties(customer, entityVo);

		customerService.save(customer);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/business/customer/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		customerService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
