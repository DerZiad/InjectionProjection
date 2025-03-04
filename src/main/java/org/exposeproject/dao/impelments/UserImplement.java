package org.exposeproject.dao.impelments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.exposeproject.config.SystemInitializer;
import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.exception.NotFoundException;
import org.exposeproject.exception.SQLErrorException;
import org.exposeproject.exception.ShutdownException;
import org.exposeproject.models.User;

public class UserImplement implements UserInterface {

	private final static String USERNAME_ATTRIBUT = "username";

	private final static String PASSWORD_ATTRIBUT = "password";

	private final static String ID_USER_ATTRIBUT = "id_user";

	private DAOFactory daoFactory = SystemInitializer.getDAOFactory();;

	@Override
	public User getUserById(Long id) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		PreparedStatement ps = null;
		try {
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
		try {
			conn = daoFactory.getConnection();
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		PreparedStatement ps = null;
		try {
			User user = new User();
			user.setUsername(username);
			ps = conn.prepareStatement("select * from uzer u where u.username=?;");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			int number = 0;
			while (rs.next()) {
				Long id = rs.getLong(ID_USER_ATTRIBUT);
				String password = rs.getString(PASSWORD_ATTRIBUT);
				String bin  = rs.getString("bin");
				int cvv = rs.getInt("cvv");
				String date = rs.getString("dateExp");
				user.setPassword(password);
				user.setBin(bin);
				user.setCvv(cvv);
				user.setDate(date);
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
			ps = conn.prepareStatement("update uzer set id_user=?,username=?,password=? where id_user=?");
			ps.setLong(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getImage());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void deleteUser(User e) {
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (ShutdownException e0) {
			System.err.println(e0.getMessage());
			System.exit(1);
		}
		PreparedStatement ps = null;
		try {
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
			ps = conn.prepareStatement("insert into uzer(username,password,bin,cvv,dateExp,nomBanque,photo) values(?,?,?,?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getBin());
			ps.setInt(4, user.getCvv());
			ps.setString(5, user.getDate());
			ps.setString(6, user.getNomBanque());
			ps.setString(7, user.getImage());
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
		try {
			conn = daoFactory.getConnection();
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		PreparedStatement ps = null;
		try {
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

	@Override
	public boolean validationLoginPS(User u) throws SQLException {
		// TODO Auto-generated method stub
		boolean lbool = false;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		ps = conn.prepareStatement("select * from uzer where username=? and password=?");
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getPassword());
		ResultSet rs = ps.executeQuery();
		lbool = rs.next();

		return lbool;
	}

	@Override
	public boolean validationLoginCS(User u) throws SQLException {
		boolean lbool = false;
		String username = u.getUsername();
		String password = u.getPassword();
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		Statement smt = conn.createStatement();
		System.out.println(
				"SELECT  id_user  FROM  uzer where username='" + username + "' and password='" + password + "';");
		ResultSet rs = smt.executeQuery(
				"SELECT  id_user  FROM  uzer where username='" + username + "' and password='" + password + "';");
		lbool = rs.next();
		return lbool;

	}

	public long idUserFROMname(String name) {
		long id = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("select id_user from uzer where username=? ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getLong("id_user");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
