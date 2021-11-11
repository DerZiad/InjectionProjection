package org.exposeproject.dao.interfaces;

import org.exposeproject.models.Message;
import org.exposeproject.models.User;

public interface MessageInterface {
	
	public Message getMessageById(Long id);
	
	//public Message getMessageById(String username);
	
	//public Message editMessage(User user);
	
	public void deleteMessage(User e);
	
	public Message addMessage(User user);

	public Message getMessageByUser(String username);

	public Message editMessage(Message msg);
}
