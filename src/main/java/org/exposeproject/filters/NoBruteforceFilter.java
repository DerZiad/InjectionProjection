package org.exposeproject.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.config.DaoConfig;
import org.exposeproject.dao.impelments.NotFoundException;
import org.exposeproject.dao.impelments.SQLErrorException;
import org.exposeproject.dao.interfaces.AuthenticationInterface;
import org.exposeproject.models.Authentication;

@WebFilter("/login")
public class NoBruteforceFilter implements Filter {

	private AuthenticationInterface authenticationMetier;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		DAOFactory daoFactory = (DAOFactory) filterConfig.getServletContext().getAttribute(DaoConfig.ATT_DAO_FACTORY);
		authenticationMetier = AuthenticationInterface.getInstance(daoFactory);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String ip = req.getRemoteAddr();
		try {
			Authentication auth = authenticationMetier.getAuthenticationById(ip);
			if (auth.getNumber() < 5) {
				if (req.getMethod().equals("POST")) {
					auth.setNumber(auth.getNumber() + 1);
					authenticationMetier.editAuthentication(auth);
				}
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect("https://www.facebook.com");
			}
		} catch (SQLErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			Authentication auth = new Authentication();
			auth.setIp(ip);
			auth.setNumber(1);
			try {
				authenticationMetier.addAuthentication(auth);
			} catch (SQLErrorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			chain.doFilter(request, response);
		}

	}

}
