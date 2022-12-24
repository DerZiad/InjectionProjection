package org.exposeproject.dao.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.exposeproject.exception.ShutdownException;

@WebListener
public class DatabaseStarterListner implements ServletContextListener {
	public static final String ATT_DAO_FACTORY = "daofactory";

	private DAOFactory daoFactory;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		try {
			this.daoFactory = DAOFactory.getInstance();
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		servletContext.removeAttribute(ATT_DAO_FACTORY);
	}
}
