package org.exposeproject.exception;

public class ShutdownException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ShutdownException(String error) {
		super(error);
	}
	
	public ShutdownException() {
		
	}
}
