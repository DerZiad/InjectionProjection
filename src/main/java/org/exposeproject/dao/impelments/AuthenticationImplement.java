package org.exposeproject.dao.impelments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.interfaces.AuthenticationInterface;
import org.exposeproject.models.Authentication;

public class AuthenticationImplement implements AuthenticationInterface{
	
	private final static String IP_ATTRIBUT = "ip";
	private final static String ID_ATTRIBUT = "id";
	private final static String TRIES_ATTRIBUT = "tries";
	
	private DAOFactory daoFactory;
	
	public AuthenticationImplement(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Authentication getAuthenticationById(Long id) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			Authentication auth = new Authentication();
			auth.setId(id);
			ps = conn.prepareStatement("select * from Authentication a where a.id_authentication=?;");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			int number = 0;
			while (rs.next()) {
				String ip = rs.getString(IP_ATTRIBUT);
				Integer tries = rs.getInt(TRIES_ATTRIBUT);
				auth.setIp(ip);
				auth.setNumber(tries);
				number++;
				break;
			}
			if (number == 0) {
				throw new NotFoundException("L'utilisateur suivant n'existe pas");
			}
			ps.close();
			conn.close();
			return auth;
		} catch (SQLException e) {
			System.err.println("[ - ] - Erreur au niveau de SQL");
			throw new SQLErrorException("");
		}
	}

	@Override
	public Authentication editAuthentication(Authentication authentication)
			throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("update Authentication set ip=?,tries=? where id_authentication=?");
			ps.setString(1, authentication.getIp());
			ps.setInt(2, authentication.getNumber());
			ps.setLong(3, authentication.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return authentication;
	}

	@Override
	public void deleteAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException {
		this.deleteAuthenticationById(authentication.getId());
	}

	@Override
	public void deleteAuthenticationById(Long id) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("delete from Authentication a where a.id_authentication=?");
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
	public void addAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("insert into Authentication(ip,tries) values(?,?)");
			ps.setString(1, authentication.getIp());
			ps.setInt(2, authentication.getNumber());
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

	
	
}
