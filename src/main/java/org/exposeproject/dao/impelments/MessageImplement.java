package org.exposeproject.dao.impelments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.interfaces.MessageInterface;
import org.exposeproject.models.Message;
import org.exposeproject.models.User;

public class MessageImplement implements MessageInterface {
	private DAOFactory daoFactory;

	public MessageImplement(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public Message getMessageById(Long id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement ps;
		Message m = new Message();
		try {
			ps = conn.prepareStatement("select message,fid_user  from message where id_message=? ");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				m.setMessage(rs.getString("message"));
				m.setIdUser(rs.getLong("fid_user"));
				m.setId(rs.getLong("id_message"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return m;

	}

	@Override
	public Message getMessageByUser(String username) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement ps;
		Message m = new Message();
		try {
			ps = conn.prepareStatement(
					"select message,id_message,fid_user  from message m ,uzer u where u.username=? and u.id_user=m.fid_user    ");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				m.setMessage(rs.getString("message"));
				m.setIdUser(rs.getLong("fid_user"));
				m.setId(rs.getLong("id_message"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return m;
	}

	@Override
	public Message editMessage(Message msg) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("update message  set message=? where id_message=?");
			ps.setString(1, msg.getMessage());
			ps.setLong(2, msg.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public void deleteMessage(User e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("insert into message(message,fid_user) values(?,?) ");
			ps.setString(1, message.getMessage());
			ps.setLong(2, message.getIdUser());
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
