package org.exposeproject.dao.interfaces;

import org.exposeproject.models.User;

public interface UserInterface {
	
	public User getUserById(Long id);
	
	public User getUserById(String username);
	
	public User editUser(User user);
	
	public void deleteUser(User e);
	
	public User addUser(User user);
}
