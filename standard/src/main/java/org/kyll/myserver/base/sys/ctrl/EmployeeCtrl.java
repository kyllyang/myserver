package org.kyll.myserver.base.sys.ctrl;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.sys.entity.Config;
import org.kyll.myserver.base.sys.entity.Department;
import org.kyll.myserver.base.sys.entity.Employee;
import org.kyll.myserver.base.sys.service.ConfigService;
import org.kyll.myserver.base.sys.service.EmployeeService;
import org.kyll.myserver.base.sys.vo.SessionVo;
import org.kyll.myserver.base.sys.vo.EmployeeVo;
import org.kyll.myserver.base.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private ConfigService configService;

	@RequestMapping("/login.ctrl")
	public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee loginUser = employeeService.login(username, password);
		String contextPath = request.getContextPath();
		if (loginUser == null) {
			response.sendRedirect(contextPath + "/login.jsp");
		} else {
			SessionVo sessionVo = new SessionVo();
			sessionVo.setUserId(loginUser.getId());
			sessionVo.setUsername(loginUser.getUsername());

			List<Config> configList = configService.getAll();
			Map<String, String> configMap = new HashMap<>();
			for (Config config : configList) {
				configMap.put(config.getKey(), config.getValue());
			}
			sessionVo.setConfig(configMap);

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

	@RequestMapping("/sys/employee/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Employee> dataset = employeeService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<EmployeeVo> voDataset = POJOUtils.convert(dataset, EmployeeVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/sys/employee/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Employee entity = employeeService.get(id);
		EmployeeVo entityVo = POJOUtils.convert(entity, EmployeeVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/sys/employee/save.ctrl")
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

		boolean result = employeeService.save(employee, entityVo.getDepartmentId());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(result));
	}

	@RequestMapping("/sys/employee/saveRole.ctrl")
	public void saveRole(Long employeeId, Long[] roleIds, HttpServletResponse response) throws Exception {
		employeeService.save(employeeId, roleIds);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/sys/employee/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		employeeService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<Employee, EmployeeVo> voHandler = new POJOUtils.VoHandler<Employee, EmployeeVo>() {
		@Override
		public void handler(Employee employee, EmployeeVo employeeVo) {
			Department department = employee.getDepartment();
			if (department != null) {
				employeeVo.setDepartmentId(department.getId());
				employeeVo.setDepartmentName(department.getName());
			}
		}
	};
}
