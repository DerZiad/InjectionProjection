package org.exposeproject.dao.interfaces;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.impelments.MessageImplement;
import org.exposeproject.dao.impelments.UserImplement;
import org.exposeproject.models.Message;
import org.exposeproject.models.User;

public interface MessageInterface {
	
	public Message getMessageById(Long id);
	
	//public Message getMessageById(String username);
	
	//public Message editMessage(User user);
	
	public void deleteMessage(User e);
	
	public Message addMessage(Message message);

	public Message getMessageByUser(String username);

	public Message editMessage(Message msg);
	
	public static MessageInterface getInstance(DAOFactory daoFactory) {
		return new MessageImplement(daoFactory);
	}
}
