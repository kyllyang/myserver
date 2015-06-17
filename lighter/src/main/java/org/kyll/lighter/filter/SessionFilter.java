package org.kyll.lighter.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: Kyll
 * Date: 2014-11-07 14:20
 */
public class SessionFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			String contextPath = request.getContextPath();
			if (!(contextPath + "/login.ctrl").equals(request.getRequestURI())) {
				HttpSession session = request.getSession();
				if (session.getAttribute("sessionVo") == null) {
					if (servletResponse instanceof HttpServletResponse) {
						HttpServletResponse response = (HttpServletResponse) servletResponse;
						response.sendRedirect(contextPath + "/login.jsp");
					}
				}
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
	}
}
