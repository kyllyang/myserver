package org.kyll.myserver.base.sys.ctrl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.sys.QueryCondition;
import org.kyll.myserver.base.sys.entity.User;
import org.kyll.myserver.base.sys.service.UserService;
import org.kyll.myserver.base.sys.vo.SessionVo;
import org.kyll.myserver.base.sys.vo.UserVo;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * User: Kyll
 * Date: 2014-11-05 15:22
 */
@Controller
@Scope("request")
public class UserCtrl {
	@Autowired
	private UserService userService;

	@RequestMapping("/login.ctrl")
	public void login(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User loginUser = userService.login(user);
		String contextPath = request.getContextPath();
		if (loginUser == null) {
			response.sendRedirect(contextPath + "/login.jsp");
		} else {
			SessionVo sessionVo = new SessionVo();
			sessionVo.setUserId(loginUser.getId());
			sessionVo.setUsername(loginUser.getUsername());

			HttpSession session = request.getSession();
			session.setAttribute("sessionVo", sessionVo);

			response.sendRedirect(contextPath + "/index.jsp");
		}
	}

	@RequestMapping("/logout.ctrl")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	@RequestMapping("/sys/user/list.ctrl")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<User> dataset = userService.get(RequestUtils.get(request, "qc", QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<UserVo> voDataset = POJOUtils.convert(dataset, UserVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/sys/user/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		User entity = userService.get(id);
		UserVo entityVo = POJOUtils.convert(entity, UserVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/sys/user/save.ctrl")
	public void save(UserVo entityVo, HttpServletResponse response) throws Exception {
		boolean result = userService.save(POJOUtils.convert(entityVo, User.class, userService));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(result));
	}

	@RequestMapping("/sys/user/saveRole.ctrl")
	public void saveModule(Long userId, Long[] roleIds, HttpServletResponse response) throws Exception {
		userService.save(userId, roleIds);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/sys/user/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		userService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
