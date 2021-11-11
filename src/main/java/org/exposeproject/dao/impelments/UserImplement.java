package org.exposeproject.dao.impelments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.models.User;

public class UserImplement implements UserInterface {

	private final static String USERNAME_ATTRIBUT = "username";

	private final static String PASSWORD_ATTRIBUT = "password";

	private final static String ID_USER_ATTRIBUT = "id_user";

	private DAOFactory daoFactory;

	public UserImplement(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public User getUserById(Long id) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			User user = new User();
			user.setId(id);
			ps = conn.prepareStatement("select * from uzer u where u.id_user=?;");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			int number = 0;
			while (rs.next()) {
				String username = rs.getString(USERNAME_ATTRIBUT);
				String password = rs.getString(PASSWORD_ATTRIBUT);
				user.setPassword(password);
				user.setUsername(username);
				number++;
				break;
			}
			if (number == 0) {
				throw new NotFoundException("L'utilisateur suivant n'existe pas");
			}
			ps.close();
			conn.close();
			return user;
		} catch (SQLException e) {
			System.err.println("[ - ] - Erreur au niveau de SQL");
			throw new SQLErrorException("");
		}
	}

	@Override
	public User getUserById(String username) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			User user = new User();
			user.setUsername(username);
			ps = conn.prepareStatement("select * from uzer u where u.username=?;");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			int number = 0;
			while (rs.next()) {
				Long id = rs.getLong(ID_USER_ATTRIBUT);
				String password = rs.getString(PASSWORD_ATTRIBUT);
				user.setPassword(password);
				user.setId(id);
				number++;
				break;
			}
			if (number == 0) {
				throw new NotFoundException("L'utilisateur suivant n'existe pas");
			}
			ps.close();
			conn.close();
			return user;
		} catch (SQLException e) {
			System.err.println("[ - ] - Erreur au niveau de SQL");
			throw new SQLErrorException("");
		}
	}

	@Override
	public User editUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("update etablissement set id_user=?,username=?,password=? where id_user=?");
			ps.setLong(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setBytes(4, user.getImage());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void deleteUser(User e) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("delete from uzer u where u.id_user=?");
			ps.setLong(1, e.getId());
			ps.execute();
		} catch (SQLException e1) {
			System.err.println("[ - ] - Connection is lost");
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e2) {
				System.err.println("[ - ] - Connection is lost");
			}
		}

	}

	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("insert into uzer(username,password) values(?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("[ - ] - Connection is lost");
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e2) {
				System.err.println("[ - ] - Connection is lost");
			}
		}
	}

	@Override
	public void deleteUser(Long id) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("delete from uzer u where u.id_user=?");
			ps.setLong(1, id);
			ps.execute();
		} catch (SQLException e1) {
			System.err.println("[ - ] - Connection is lost");
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e2) {
				System.err.println("[ - ] - Connection is lost");
			}
		}

	}

}
