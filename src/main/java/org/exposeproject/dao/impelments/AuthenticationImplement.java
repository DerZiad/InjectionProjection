package org.exposeproject.dao.impelments;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.exposeproject.config.SystemInitializer;
import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.interfaces.AuthenticationInterface;
import org.exposeproject.exception.NotFoundException;
import org.exposeproject.exception.SQLErrorException;
import org.exposeproject.exception.ShutdownException;
import org.exposeproject.models.Authentication;

public class AuthenticationImplement implements AuthenticationInterface{
	
	private final static String TRIES_ATTRIBUT = "tries";
	private final static String START_ATTRIBUT = "start";
	private DAOFactory daoFactory = SystemInitializer.getDAOFactory();
	
	@Override
	public Authentication getAuthenticationById(String ip) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			Authentication auth = new Authentication();
			ps = conn.prepareStatement("select * from authentication a where a.ip=?;");
			ps.setString(1, ip);
			auth.setIp(ip);
			ResultSet rs = ps.executeQuery();
			int number = 0;
			while (rs.next()) {
				Integer tries = rs.getInt(TRIES_ATTRIBUT);
				auth.setNumber(tries);
				BigDecimal decimal = rs.getBigDecimal(START_ATTRIBUT);
				auth.setStart(decimal.longValue());
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
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
			return null;//System will shutdown
		}
	}

	@Override
	public Authentication editAuthentication(Authentication authentication)
			throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("update authentication set tries=?,start=? where ip=?");
			ps.setInt(1, authentication.getNumber());
			ps.setBigDecimal(2, new BigDecimal(authentication.getStart()));
			ps.setString(3, authentication.getIp());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return authentication;
	}

	@Override
	public void deleteAuthentication(Authentication authentication) throws SQLErrorException, NotFoundException {
		this.deleteAuthenticationById(authentication.getIp());
	}

	@Override
	public void deleteAuthenticationById(String ip) throws SQLErrorException, NotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("delete from authentication a where a.ip=?");
			ps.setString(1, ip);
			ps.execute();
		} catch (SQLException e1) {
			System.err.println("[ - ] - Connection is lost");
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
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
			ps = conn.prepareStatement("insert into authentication(ip,tries,start) values(?,?,?)");
			ps.setString(1, authentication.getIp());
			ps.setInt(2, authentication.getNumber());
			ps.setBigDecimal(3, new BigDecimal(authentication.getStart()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("[ - ] - Connection is lost");
		} catch (ShutdownException e) {
			System.err.println(e.getMessage());
			System.exit(1);
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
