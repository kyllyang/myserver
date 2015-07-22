package org.kyll.myserver.business.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.sys.entity.Department;
import org.kyll.myserver.business.entity.Area;
import org.kyll.myserver.business.entity.Employee;
import org.kyll.myserver.business.entity.Role;
import org.kyll.myserver.business.service.AreaService;
import org.kyll.myserver.business.service.EmployeeService;
import org.kyll.myserver.base.sys.vo.SessionVo;
import org.kyll.myserver.business.service.RoleService;
import org.kyll.myserver.business.vo.EmployeeVo;
import org.kyll.myserver.base.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2014-11-05 15:22
 */
@Controller
@Scope("request")
public class EmployeeCtrl {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/login.ctrl")
	public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee employee = employeeService.login(username, password);
		String contextPath = request.getContextPath();
		if (employee == null) {
			response.sendRedirect(contextPath + "/login.jsp");
		} else {
			SessionVo sessionVo = new SessionVo();
			sessionVo.setUserId(employee.getId());
			sessionVo.setName(employee.getName());
			sessionVo.setUsername(employee.getUsername());
			for (Role role : employee.getRoleSet()) {
			}
			sessionVo.setRoleSet(employee.getRoleSet());

			HttpSession session = request.getSession();
			session.setAttribute(ConstUtils.SESSION_NAME, sessionVo);

			response.sendRedirect(contextPath + "/index.jsp");
		}
	}

	@RequestMapping("/logout.ctrl")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	@RequestMapping("/business/employee/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Employee> dataset = employeeService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<EmployeeVo> voDataset = POJOUtils.convert(dataset, EmployeeVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/employee/tree.ctrl")
	public void tree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryCondition qc = new QueryCondition();
		if (!RequestUtils.isAdmin(request)) {
			qc.setEmployeeId(RequestUtils.getSessionVo(request).getUserId());
		}
		JSONArray ja = employeeService.getTreeJson(qc);

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}

	@RequestMapping("/business/employee/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Employee entity;
		if (id == null) {
			entity = new Employee();
		} else {
			entity = employeeService.get(id);
		}
		EmployeeVo entityVo = POJOUtils.convert(entity, EmployeeVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/business/employee/save.ctrl")
	public void save(EmployeeVo entityVo, HttpServletResponse response) throws Exception {
		Employee employee;
		Long id = entityVo.getId();
		if (id == null) {
			employee = new Employee();
		} else {
			employee = employeeService.get(entityVo.getId());
		}

		String oldPassword = employee.getPassword();
		POJOUtils.copyProperties(employee, entityVo);
		if ("0".equals(entityVo.getPasswordReset())) {
			employee.setPassword(oldPassword);
		} else {
			employee.setPassword(StringUtils.encryptSHA(employee.getPassword()));
		}

		boolean result = employeeService.save(employee, entityVo.getAreaIds(), entityVo.getRoleIds());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(result));
	}

	@RequestMapping("/business/employee/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		employeeService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private List<Area> areaList;
	private List<Role> roleList;

	private POJOUtils.VoHandler<Employee, EmployeeVo> voHandler = (employee, employeeVo) -> {
		Set<Area> areaSet = employee.getAreaSet();
		if (areaSet != null) {
			Long[] areaIds = new Long[areaSet.size()];
			int i = 0;
			for (Area area : areaSet) {
				areaIds[i++] = area.getId();
			}
			employeeVo.setAreaIds(areaIds);
		}
		Set<Role> roleSet = employee.getRoleSet();
		if (roleSet != null) {
			Long[] roleIds = new Long[roleSet.size()];
			int i = 0;
			for (Role role : roleSet) {
				roleIds[i++] = role.getId();
			}
			employeeVo.setRoleIds(roleIds);
		}

		if (areaList == null) {
			areaList = areaService.get();
		}
		employeeVo.setAreaList(areaList);

		if (roleList == null) {
			roleList = roleService.get();
		}
		employeeVo.setRoleList(roleList);
	};
}
