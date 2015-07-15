package org.kyll.myserver.business.ctrl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Customer;
import org.kyll.myserver.business.entity.Project;
import org.kyll.myserver.business.service.CustomerService;
import org.kyll.myserver.business.service.ProjectService;
import org.kyll.myserver.business.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-07-15 8:50
 */
@Controller
@Scope("request")
public class ProjectCtrl {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/business/project/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Project> dataset = projectService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ProjectVo> voDataset = POJOUtils.convert(dataset, ProjectVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/project/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Project entity = projectService.get(id);
		ProjectVo entityVo = POJOUtils.convert(entity, ProjectVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/business/project/save.ctrl")
	public void save(ProjectVo entityVo, HttpServletResponse response) throws Exception {
		Project project;
		Long id = entityVo.getId();
		if (id == null) {
			project = new Project();
		} else {
			project = projectService.get(id);
		}
		Long customerId = entityVo.getCustomerId();
		if (customerId != null) {
			project.setCustomer(customerService.get(customerId));
		}
		POJOUtils.copyProperties(project, entityVo);

		projectService.save(project);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/business/project/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		projectService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<Project, ProjectVo> voHandler = (project, projectVo) -> {
		Customer customer = project.getCustomer();
		if (customer != null) {
			projectVo.setCustomerId(customer.getId());
			projectVo.setCustomerCompanyName(customer.getCompanyName());
		}
	};
}
