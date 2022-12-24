package org.exposeproject.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.exposeproject.dao.impelments.MessageImplement;
import org.exposeproject.exception.NotFoundException;
import org.exposeproject.exception.SQLErrorException;
import org.exposeproject.models.Message;

public interface MessageInterface {
	
	public Message getMessageById(Long id);
	
	public void addMessage(Message message);

	public Message getMessageByUser(String username);

	public Message editMessage(Message msg);
	
	
	public List<Message> getListMessage();

	public void deleteMessage(String msg);

	
	public List<Message> getMessagePrive(String username) throws SQLErrorException, NotFoundException;

	public List<Message> getMessageParBanqueCS(String banque, Long id_username)  throws SQLException;

	public List<Message> getMessageParBanquePS(String rech, Long id_username)throws SQLException;
	
	public static MessageInterface getInstance() {
		return new MessageImplement();
	}
}
