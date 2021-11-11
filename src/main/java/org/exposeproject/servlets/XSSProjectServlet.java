package org.exposeproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.config.DaoConfig;
import org.exposeproject.dao.impelments.NotFoundException;
import org.exposeproject.dao.impelments.SQLErrorException;
import org.exposeproject.dao.interfaces.MessageInterface;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.models.Message;
import org.exposeproject.models.User;
@WebServlet("/formulaire")
public class XSSProjectServlet extends HttpServlet{
	
	private final static String PATH_FORUM = "/WEB-INF/forum.jsp";
	
	private DAOFactory daoFactory;
	
	private UserInterface userRepository;
	private MessageInterface messageInterface;
	
	@Override
	public void init() throws ServletException {
		this.daoFactory = (DAOFactory)this.getServletContext().getAttribute(DaoConfig.ATT_DAO_FACTORY);
		userRepository = UserInterface.getInstance(daoFactory);
		messageInterface = MessageInterface.getInstance(daoFactory);
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User("ziadbougrine","ziadbougrine", new byte[8]);
		try {
			userRepository.addUser(user);
			//messageInterface.addMessage(user)
		} catch (SQLErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher(PATH_FORUM).forward(req, resp);
	}
}
