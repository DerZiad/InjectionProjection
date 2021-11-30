package org.exposeproject.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.impelments.MessageImplement;
import org.exposeproject.dao.impelments.NotFoundException;
import org.exposeproject.dao.impelments.SQLErrorException;
import org.exposeproject.dao.impelments.UserImplement;
import org.exposeproject.models.Message;
import org.exposeproject.models.MessageUser;
import org.exposeproject.models.User;

public interface MessageInterface {
	
	public Message getMessageById(Long id);
	
	public void addMessage(Message message);

	public Message getMessageByUser(String username);

	public Message editMessage(Message msg);
	
	public static MessageInterface getInstance(DAOFactory daoFactory) {
		return new MessageImplement(daoFactory);
	}
	public List<Message> getListMessage();

	public void deleteMessage(String msg);

	public List<Message> getMessagePrive(String username) throws SQLErrorException, NotFoundException;

	public List<Message> getMessageParBanqueCS(String banque, Long id_username)  throws SQLException;

	public List<Message> getMessageParBanquePS(String rech, Long id_username)throws SQLException;
}
