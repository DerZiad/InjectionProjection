package org.exposeproject.exception;

public class SQLErrorException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public SQLErrorException(String msg) {
		super(msg);
	}
	
	public SQLErrorException() {
	}
	
}
