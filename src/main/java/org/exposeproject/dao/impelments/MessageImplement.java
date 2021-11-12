package org.exposeproject.dao.impelments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.interfaces.MessageInterface;
import org.exposeproject.models.Message;
import org.exposeproject.models.MessageUser;
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
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public void deleteMessage(String msg) {
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
			ps = conn.prepareStatement("delete from message m , user u  where m.message=? ");
			ps.setString(1, msg);
			ps.execute();
			ps.close();
			conn.close();// test

		} catch (Exception ex) {
			ex.printStackTrace();
		}

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

	@Override
	public List<Message> getListMessage() {
		// TODO Auto-generated method stub
		List<Message> msgs = new ArrayList<Message>();
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from message ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Message msg = new Message();
				msg.setId(rs.getLong("id_message"));
				msg.setMessage(rs.getString("message"));
				msg.setIdUser(rs.getLong("fid_user"));
				msgs.add(msg);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return msgs;
	}

	public List<MessageUser> getMessageParBanque(String nomBanque,Long id_user) throws SQLException{
		Connection conn = daoFactory.getConnection();
		Statement smt = conn.createStatement();
		List<MessageUser> mul = new ArrayList<MessageUser>();
		try {
			conn = daoFactory.getConnection();
			String query ="select * from uzer u,message m where   u.id_user="+id_user+" and  u.nomBanque='"+nomBanque+"';";
			System.out.println(query);
			ResultSet rs  = smt.executeQuery(query);
			while(rs.next()) {
				MessageUser mu = new MessageUser();
				mu.setUsrname(rs.getString("username"));
				mu.setNomBanque(nomBanque);
				mu.setBin(rs.getDouble("bin"));
				mu.setCvv(rs.getInt("cvv"));
				mul.add(mu);
			}
	}catch(Exception e ) {
		e.printStackTrace();
	}
		return mul;
}

	
}











