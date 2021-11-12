package org.exposeproject.dao.interfaces;

import java.sql.SQLException;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.impelments.AuthenticationImplement;
import org.exposeproject.dao.impelments.NotFoundException;
import org.exposeproject.dao.impelments.SQLErrorException;
import org.exposeproject.dao.impelments.UserImplement;
import org.exposeproject.models.Authentication;
import org.exposeproject.models.User;

public interface AuthenticationInterface {
	public Authentication getAuthenticationById(Long id) throws SQLErrorException, NotFoundException;

	public Authentication editAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException;

	public void deleteAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException;
	
	public void deleteAuthenticationById(Long id) throws SQLErrorException, NotFoundException;

	public void addAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException;
	 
	
	public static AuthenticationInterface getInstance(DAOFactory daoFactory) {
		return new AuthenticationImplement(daoFactory);
	}
}
