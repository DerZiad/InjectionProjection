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

import org.exposeproject.models.User;

@WebFilter("/*")
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getServletPath();

		boolean test = false;

		List<String> list = Arrays.asList("/login", "/home", "/signup", "/css", "/fonts", "/images", "/js", "/vendor",
				"/aquire.otf", "/style.css","/pictures", "/main.js","/index.jsp");

		for (String item : list) {
			if (path.startsWith(item))
				test = true;
		}

		if (test) {
			chain.doFilter(req, resp);
		} else {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("username");
			if (user != null) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect("login");
			}
		}
	}

}
