package edu.sjsu.students.shuangwu.opinions.web;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserLoginHandlerInterceptorAdapter extends
		HandlerInterceptorAdapter {
	private static final Logger LOGGER = Logger
			.getLogger(UserLoginHandlerInterceptorAdapter.class);

	private Set<String> publicUrls;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object loginUserInSession = request.getSession().getAttribute(
				"loginUser");
		String uri = request.getRequestURI();
		LOGGER.debug("Checking if the URL is public: " + uri);
		if ((loginUserInSession == null) && publicUrls.contains(uri)) {
			// unauthorized user can access public URLs
			LOGGER.debug("unauthorized user can access public URLs");
			return true;
		} else if ((loginUserInSession == null) && (!publicUrls.contains(uri))) {
			// unauthorized user cannot access non-public URLs, will go to login
			// page
			LOGGER.debug("unauthorized user cannot access non-public URLs, will go to login page");
			response.sendRedirect("/login.do");
			return false;
		} else {
			return true;
		}
	}

	public void setPublicUrls(Set<String> publicUrls) {
		this.publicUrls = publicUrls;
	}

}
