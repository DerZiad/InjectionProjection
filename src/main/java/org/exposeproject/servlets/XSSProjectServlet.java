package org.exposeproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.exposeproject.dao.interfaces.AuthenticationInterface;
import org.exposeproject.dao.interfaces.MessageInterface;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.models.Message;
import org.exposeproject.models.User;

@WebServlet("/formulaire")
public class XSSProjectServlet extends HttpServlet {

	private final static String PATH_FORUM = "/WEB-INF/forum.jsp";
	
	private final static String ATTRIBUT_MESSAGES = "messages";
	private final static String ATTRIBUT_MESSAGE = "message";
	
	private final static String REDIRECT_FORUM = "formulaire";
	
	private DAOFactory daoFactory;

	private UserInterface userRepository;
	private MessageInterface messageInterface;
	private AuthenticationInterface authenticationInterface;
	
	@Override
	public void init() throws ServletException {
		this.daoFactory = (DAOFactory) this.getServletContext().getAttribute(DaoConfig.ATT_DAO_FACTORY);
		userRepository = UserInterface.getInstance(daoFactory);
		messageInterface = MessageInterface.getInstance(daoFactory);
		authenticationInterface = AuthenticationInterface.getInstance(daoFactory);
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message =(String) req.getParameter(ATTRIBUT_MESSAGE);
		if(message != null) {
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("username");
			Message messageObject = new Message(message, user.getId());
			messageInterface.addMessage(messageObject); 
		}
		resp.sendRedirect(REDIRECT_FORUM);
	}	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ip = req.getRemoteAddr();
		try {
			authenticationInterface.deleteAuthenticationById(ip);
		} catch (SQLErrorException e1) {
			e1.printStackTrace();
		} catch (NotFoundException e1) {
			e1.printStackTrace();
		}
		List<Message> messages = messageInterface.getListMessage();
		messages.stream().forEach(mess -> {
			try {
				mess.setUser(userRepository.getUserById(mess.getIdUser()));
			} catch (SQLErrorException e) {
				e.printStackTrace();
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		});
		req.setAttribute("messages", messages);

		this.getServletContext().getRequestDispatcher(PATH_FORUM).forward(req, resp);
	}
}
