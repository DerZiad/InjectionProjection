package org.exposeproject.dao.config;

import java.io.IOException;

public class DAOConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOConfigurationException(String msg) {
		super(msg);
	}

	public DAOConfigurationException() {

	}

	public DAOConfigurationException(String msg,IOException io) {
		super(msg);
	}
	
	public DAOConfigurationException(String msg,ClassNotFoundException io) {
		super(msg);
	}
}
