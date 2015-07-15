package org.kyll.myserver.business.ctrl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Project;
import org.kyll.myserver.business.entity.ProjectTrace;
import org.kyll.myserver.business.service.ProjectService;
import org.kyll.myserver.business.service.ProjectTraceService;
import org.kyll.myserver.business.vo.ProjectTraceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-07-15 10:40
 */
@Controller
@Scope("request")
public class ProjectTraceCtrl {
	@Autowired
	private ProjectTraceService projectTraceService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping("/business/projecttrace/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<ProjectTrace> dataset = projectTraceService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ProjectTraceVo> voDataset = POJOUtils.convert(dataset, ProjectTraceVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/projecttrace/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		ProjectTrace entity = projectTraceService.get(id);
		ProjectTraceVo entityVo = POJOUtils.convert(entity, ProjectTraceVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/business/projecttrace/save.ctrl")
	public void save(ProjectTraceVo entityVo, HttpServletResponse response) throws Exception {
		ProjectTrace projectTrace;
		Long id = entityVo.getId();
		if (id == null) {
			projectTrace = new ProjectTrace();
			Long projectId = entityVo.getProjectId();
			if (projectId != null) {
				projectTrace.setProject(projectService.get(projectId));
			}
		} else {
			projectTrace = projectTraceService.get(id);
		}
		POJOUtils.copyProperties(projectTrace, entityVo);

		projectTraceService.save(projectTrace);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/business/projecttrace/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		projectTraceService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<ProjectTrace, ProjectTraceVo> voHandler = (projectTrace, projectTraceVo) -> {
		Project project = projectTrace.getProject();
		if (project != null) {
			projectTraceVo.setProjectId(project.getId());
			projectTraceVo.setProjectName(project.getName());
		}
	};
}
