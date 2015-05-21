package org.kyll.myserver.base.sys.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.sys.entity.Department;
import org.kyll.myserver.base.sys.service.DepartmentService;
import org.kyll.myserver.base.sys.vo.DepartmentVo;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-05-21 18:09
 */
@Controller
@Scope("request")
public class DepartmentCtrl {
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/sys/department/tree.ctrl")
	public void tree(Boolean checked, HttpServletResponse response) throws Exception {
		JSONArray ja = departmentService.getTreeJson(checked);

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}

	@RequestMapping("/sys/department/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Department entity = departmentService.get(id);
		DepartmentVo entityVo = POJOUtils.convert(entity, DepartmentVo.class, (department, departmentVo) -> {
			Department parent = department.getParent();
			if (parent != null) {
				departmentVo.setParentId(parent.getId());
				departmentVo.setParentName(parent.getName());
			}
		});

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/sys/department/save.ctrl")
	public void save(DepartmentVo entityVo, HttpServletResponse response) throws Exception {
		departmentService.save(POJOUtils.convert(entityVo, Department.class, departmentService), entityVo.getParentId());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/sys/department/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		departmentService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
