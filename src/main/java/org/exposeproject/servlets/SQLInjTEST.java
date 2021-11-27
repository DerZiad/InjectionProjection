package org.exposeproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.config.DaoConfig;
import org.exposeproject.dao.interfaces.MessageInterface;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.models.MessageUser;

@WebServlet(name = "userCheck1", urlPatterns = { "/userCheck1","/forumprive","/test" })
public class SQLInjTEST extends HttpServlet {
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

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>FORUM</h1><br/><br/>");
		try {

			out.println("<html>");
			out.println("<head>");
			out.println("<title>FORUM</title>");
			out.println("</head>");
			out.println("<body>");
			String user = request.getParameter("user");
			System.out.println(user);
			System.out.println("MySQL Connect Example.");
			Connection conn = null;
			/*String url = "jdbc:mysql://192.168.2.128:3306/";
			String dbName = "test";
			String driver = "com.mysql.cj.jdbc.Driver";
			String userName = "root";
			String password = "Ayman2002+";*/
			try {
				//Class.forName(driver).newInstance();
				conn = daoFactory.getConnection();
				System.out.println("Connected to the database");
				
				//PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM  uzer where id_user=?");
				//preparedStatement.setString(1, user);
				
				//ResultSet res = preparedStatement.executeQuery();
				Statement smt = conn.createStatement();
				String query ="SELECT * FROM  uzer where username= ' "+user+";";
				System.out.println(query);
				ResultSet rs = smt.executeQuery(query);
				out.println("<br/><br/>Results");
				while (rs.next()) {
					// int i = res.getInt("Emp_code");
					String s = rs.getString("username");
					String ss = rs.getString("password");
				}

				conn.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.println("</body>");
			out.println("</html>");

		} finally {
			out.close();
		}
	}
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		 HttpSession session = request.getSession();
		 List<MessageUser> mu =null;
		if(path.equals("/userCheck1")) {
		processRequest(request, response);
		}if(path.equals("/test")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/forumPRIVE.jsp").forward(request, response);

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		 HttpSession session = req.getSession();
		 List<MessageUser> mu =null;
		if(path.equals("/forumprive")){
			String banque = req.getParameter("fprv");
			Long id_username = (Long) session.getAttribute("id_user");
			System.out.println(id_username);
			try {
				mu = messageInterface.getMessageParBanque(banque, id_username);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(mu);
			req.setAttribute("message", mu);
			this.getServletContext().getRequestDispatcher("/WEB-INF/forumPRIVE.jsp").forward(req, resp);

			}
	}
}