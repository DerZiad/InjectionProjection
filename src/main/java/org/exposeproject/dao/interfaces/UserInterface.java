package org.exposeproject.dao.interfaces;

import java.sql.SQLException;

import org.exposeproject.dao.impelments.UserImplement;
import org.exposeproject.exception.NotFoundException;
import org.exposeproject.exception.SQLErrorException;
import org.exposeproject.models.User;

public interface UserInterface {

	public User getUserById(Long id) throws SQLErrorException, NotFoundException;

	public User getUserById(String username) throws SQLErrorException, NotFoundException;

	public User editUser(User user) throws SQLErrorException, NotFoundException;

	public void deleteUser(User e) throws SQLErrorException, NotFoundException;
	
	public void deleteUser(Long id) throws SQLErrorException, NotFoundException;

	public void addUser(User user) throws SQLErrorException, NotFoundException;
	 
	public boolean validationLoginPS(User u) throws SQLException;
	
	public boolean validationLoginCS(User u) throws SQLException;
	public long		idUserFROMname(String name );
	
	public static UserInterface getInstance() {
		return new UserImplement();
	}
}
