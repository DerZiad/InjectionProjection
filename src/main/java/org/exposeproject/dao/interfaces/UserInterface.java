package org.exposeproject.dao.interfaces;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.impelments.NotFoundException;
import org.exposeproject.dao.impelments.SQLErrorException;
import org.exposeproject.dao.impelments.UserImplement;
import org.exposeproject.models.User;

public interface UserInterface {

	public User getUserById(Long id) throws SQLErrorException, NotFoundException;

	public User getUserById(String username) throws SQLErrorException, NotFoundException;

	public User editUser(User user) throws SQLErrorException, NotFoundException;

	public void deleteUser(User e) throws SQLErrorException, NotFoundException;
	
	public void deleteUser(Long id) throws SQLErrorException, NotFoundException;

	public void addUser(User user) throws SQLErrorException, NotFoundException;
	
	public static UserInterface getInstance(DAOFactory daoFactory) {
		return new UserImplement(daoFactory);
	}
}
