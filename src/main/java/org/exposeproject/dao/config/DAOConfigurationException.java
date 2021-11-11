package org.exposeproject.dao.config;

import java.io.IOException;

public class DAOConfigurationException extends Exception {

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
