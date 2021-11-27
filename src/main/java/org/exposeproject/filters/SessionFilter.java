package org.exposeproject.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/*")
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getServletPath();

		boolean test = false;

		List<String> list = Arrays.asList("/login", "/css", "/fonts", "/images", "/js", "/vendor", "/aquire.otf",
				"/style.css", "/main.js");

		for (String item : list) {
			if (path.startsWith(item))
				test = true;
		}
		
		if (test) {
			chain.doFilter(req, resp);
		} else {
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");
			if (username != null) {
				chain.doFilter(request, response);
			}else {
				resp.sendRedirect("login");
			}
		}
	}

}
