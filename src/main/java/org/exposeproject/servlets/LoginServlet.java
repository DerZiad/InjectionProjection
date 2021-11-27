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

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.config.DaoConfig;
import org.exposeproject.dao.impelments.NotFoundException;
import org.exposeproject.dao.impelments.SQLErrorException;
import org.exposeproject.dao.interfaces.MessageInterface;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.models.User;

@WebServlet(name="Login",urlPatterns = { "/login", "/search" })
public class LoginServlet extends HttpServlet {

	private final static String PATH_FORUM = "/WEB-INF/forum.jsp";

	private DAOFactory daoFactory;

	private UserInterface userRepository;
	private MessageInterface messageInterface;

	@Override
	public void init() throws ServletException {
		this.daoFactory = (DAOFactory) this.getServletContext().getAttribute(DaoConfig.ATT_DAO_FACTORY);
		userRepository = UserInterface.getInstance(daoFactory);
		messageInterface = MessageInterface.getInstance(daoFactory);
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);
		if (path.equals("/login")) {
			HashMap<String, String> errors = new HashMap<String, String>();
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			if (username != null && password != null) {
				/*
				 * try { User user = userRepository.getUserById(username); if
				 * (user.getPassword().equals(password)) { // Creating Session HttpSession
				 * session = req.getSession(); session.setAttribute("username",
				 * user.getUsername()); } else { errors.put("creds",
				 * "Username or password are incorrect"); } } catch (SQLErrorException e) {
				 * e.printStackTrace(); } catch (NotFoundException e) { errors.put("creds",
				 * "Username or password are incorrect"); }
				 */
				////// METHODE LOGIN DAO/////////

				User u = new User(username, password);
				System.out.println(u.getUsername() + u.getPassword());
				/* boolean itslogged = userRepository.validationLoginPS(u);// la voici */
				boolean itslogged2 = false;
				try {
					itslogged2 = userRepository.validationLoginPS(u);
				} catch (SQLException e) {
					errors.put("error", e.getMessage());
				}

				if (itslogged2) {
					HttpSession session = req.getSession();
					session.setAttribute("username", u.getUsername());
					session.setAttribute("id_user", userRepository.idUserFROMname(username));
					System.out.println(userRepository.idUserFROMname(username));
				} else {
					errors.put("creds", "Username or password are incorrect");
				}
			}

			if (errors.size() > 0) {
				req.setAttribute("errors", errors);
				this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("formulaire");
			}
		} else {
			System.out.println("SEAARCH");
			String Search = req.getParameter("search");
			req.setAttribute("search", Search);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		}

	}
}