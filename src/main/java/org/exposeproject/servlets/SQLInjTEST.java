package org.exposeproject.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import org.exposeproject.models.Message;
import org.exposeproject.models.User;

@WebServlet("/forumprive")
public class SQLInjTEST extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOFactory daoFactory;
	private MessageInterface messageInterface;
	private final static String PATH_PRIVATE_FORUM = "/WEB-INF/forumPRIVE.jsp";

	@Override
	public void init() throws ServletException {
		this.daoFactory = (DAOFactory) this.getServletContext().getAttribute(DaoConfig.ATT_DAO_FACTORY);
		messageInterface = MessageInterface.getInstance(daoFactory);
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = null;
		List<Message> messagaesPrives = null;
		try {
			user = (User) session.getAttribute("username");
			messagaesPrives = messageInterface.getMessagePrive(user.getUsername());
			request.setAttribute("messages", messagaesPrives);
			this.getServletContext().getRequestDispatcher(PATH_PRIVATE_FORUM).forward(request, response);
		} catch (SQLErrorException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<Message> messages = null;
		String rech = req.getParameter("fprv");
		User user = (User) session.getAttribute("username");
		try {
			messages = messageInterface.getMessageParBanqueCS(rech, user.getId());
			// messages = messageInterface.getMessageParBanquePS(rech, user.getId());
		} catch (SQLException e) {
			System.out.println(req.getRemoteAddr() + " is trying to do SQL Injection");
		}
		req.setAttribute("messages", messages);
		this.getServletContext().getRequestDispatcher(PATH_PRIVATE_FORUM).forward(req, resp);

	}
}
