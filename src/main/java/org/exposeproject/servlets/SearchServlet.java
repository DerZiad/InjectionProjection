package org.exposeproject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String HOME_PATH = "/WEB-INF/index.jsp";
	private static final String ATTRIBUT_SEARCH = "search";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter(ATTRIBUT_SEARCH);
		if (search != null) {
			request.setAttribute("search", search);
		}
		this.getServletContext().getRequestDispatcher(HOME_PATH).forward(request, response);
	}

}


/*
  http://127.0.0.1:45001/ExposeProject/home?search=%22%3E%3Cscript%3Ewindow.location.replace(%22https://www.facebook.com%22);%3C/script%3E
 */
