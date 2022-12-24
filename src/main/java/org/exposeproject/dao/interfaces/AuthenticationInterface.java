package org.exposeproject.dao.interfaces;

import org.exposeproject.dao.impelments.AuthenticationImplement;
import org.exposeproject.exception.NotFoundException;
import org.exposeproject.exception.SQLErrorException;
import org.exposeproject.models.Authentication;

public interface AuthenticationInterface {
	public Authentication getAuthenticationById(String ip) throws SQLErrorException, NotFoundException;

	public Authentication editAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException;

	public void deleteAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException;
	
	public void deleteAuthenticationById(String ip) throws SQLErrorException, NotFoundException;

	public void addAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException;
	 
	
	public static AuthenticationInterface getInstance() {
		return new AuthenticationImplement();
	}
}
