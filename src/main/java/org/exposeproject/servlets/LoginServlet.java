package org.exposeproject.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.exception.NotFoundException;
import org.exposeproject.exception.SQLErrorException;
import org.exposeproject.models.User;


@WebServlet(name="log",urlPatterns = {"/login","/logout"})
public class LoginServlet extends HttpServlet {

	private final static String LOGIN_FORMULAIRE = "/WEB-INF/login.jsp";

	private UserInterface userRepository;

	@Override
	public void init() throws ServletException {
		userRepository = UserInterface.getInstance();
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(LOGIN_FORMULAIRE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);
		HttpSession session = req.getSession() ;
		if(path.equals("/login")) {
		HashMap<String, String> errors = new HashMap<String, String>();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username != null && password != null) {
			User u = new User(username, password);
			boolean itslogged2 = false;
			try {
				itslogged2 = userRepository.validationLoginCS(u);
				//SQL
				//itslogged2 = userRepository.validationLoginPS(u);
			} catch (SQLException e) {
				errors.put("error", e.getMessage());
			}

			if (itslogged2) {
				try {
					u = userRepository.getUserById(u.getUsername());
					System.out.println(u);
				} catch (SQLErrorException e) {
					e.printStackTrace();
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
				session = req.getSession();
				session.setAttribute("username", u);
			} else {
				errors.put("creds", "Username or password are incorrect");
			}
		}

		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			this.getServletContext().getRequestDispatcher(LOGIN_FORMULAIRE).forward(req, resp);
		} else {
			resp.sendRedirect("formulaire");
		}
	}else if(path.equals("/logout")) {
		session.invalidate();
		resp.sendRedirect("");
	}
}}