package org.exposeproject.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.exception.ShutdownException;

public class SystemInitializer implements ServletContextListener{
	
	private static DAOFactory daoFactory = null;
	
	private static boolean securedModus = false;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			daoFactory = DAOFactory.getInstance();
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		daoFactory = null;
		securedModus = true;
	}

	
	public static boolean isSecured() {
		return securedModus;
	}
	
	public static DAOFactory getDAOFactory() {
		return daoFactory;
	}
	
}
